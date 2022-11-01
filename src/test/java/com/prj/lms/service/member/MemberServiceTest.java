package com.prj.lms.service.member;

import com.prj.lms.domain.member.MemberEntity;
import com.prj.lms.exception.DuplicatedSignupException;
import com.prj.lms.repository.member.MemberRepository;
import com.prj.lms.requestDto.member.MemberSignupRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //테스트 클래스에 선언할경우 테스트 실행후 롤백
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PersistenceContext
    EntityManager em;

    public MemberSignupRequestDto createMemberSignupRequestDto(){
        MemberSignupRequestDto memberSignupRequestDto = new MemberSignupRequestDto();
        memberSignupRequestDto.setEmail("test@email.com");
        memberSignupRequestDto.setName("홍길동");
        memberSignupRequestDto.setAddress("서울시 마포구 합정동");
        memberSignupRequestDto.setPassword("1234");
        return memberSignupRequestDto;
    }

    @Test
    @DisplayName("회원가입 테스트")
    void saveMemberTest(){
        //given
        MemberSignupRequestDto memberSignupRequestDto = createMemberSignupRequestDto();
        //when
        MemberEntity savedMember = memberService.saveMember(memberSignupRequestDto);
        //then
        assertEquals(memberSignupRequestDto.getEmail(), savedMember.getEmail());
        assertEquals(memberSignupRequestDto.getName(), savedMember.getName());
        assertEquals(memberSignupRequestDto.getAddress(), savedMember.getAddress());
        // 비밀번호 테스트 -> passwordEncoder의 값은 해시에 솔트를 더하여 매번 달라짐 -> matches사용.
        assertTrue(passwordEncoder.matches(memberSignupRequestDto.getPassword(), savedMember.getPassword()));
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    void saveDuplicateMemberTest(){
        //given
        MemberSignupRequestDto memberSignupRequestDto1 = createMemberSignupRequestDto();
        MemberSignupRequestDto memberSignupRequestDto2 = createMemberSignupRequestDto();
        memberService.saveMember(memberSignupRequestDto1);
        //when
        Throwable e = assertThrows(DuplicatedSignupException.class, ()->{
            memberService.saveMember(memberSignupRequestDto2);
        });
        //then
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username="gildong", roles = "USER")
    public void auditingTest(){
        //given
        MemberEntity newMemberEntity = new MemberEntity();
        memberRepository.save(newMemberEntity);
        em.flush();
        em.clear();
        //when
        MemberEntity findMemberEntity = memberRepository.findById(newMemberEntity.getId()).orElseThrow(EntityNotFoundException::new);
        //then
        System.out.println("register time : " + findMemberEntity.getRegTime());
        System.out.println("update time : " + findMemberEntity.getUpdateTime());
        assertEquals("gildong", findMemberEntity.getCreatedBy());
        assertEquals("gildong", findMemberEntity.getModifiedBy());
    }
}