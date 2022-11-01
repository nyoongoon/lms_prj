package com.prj.lms.repository.cart;

import com.prj.lms.domain.cart.CartEntity;
import com.prj.lms.domain.member.MemberEntity;
import com.prj.lms.repository.member.MemberRepository;
import com.prj.lms.requestDto.member.MemberSignupRequestDto;
import com.prj.lms.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartRepositoryTest {
    @Autowired
    MemberService memberService;

    @Autowired
    CartRepository cartRepository;

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
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        MemberSignupRequestDto memberSignupRequestDto = createMemberSignupRequestDto();
        //when
        MemberEntity savedMember = memberService.saveMember(memberSignupRequestDto);
        //given
        CartEntity cartEntity = new CartEntity();
        cartEntity.setMemberEntity(savedMember); // 일대일 단방향 연관관계 매핑
        cartRepository.save(cartEntity);

        em.flush();
        em.clear();

        CartEntity savedCart = cartRepository.findById(cartEntity.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMemberEntity().getId(), savedMember.getId());
        //then
    }

}