package com.prj.lms.service.member;

import com.prj.lms.domain.member.MemberEntity;
import com.prj.lms.exception.DuplicatedSignupException;
import com.prj.lms.repository.member.MemberRepository;
import com.prj.lms.requestDto.member.MemberSignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService  {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //    public MemberEntity saveMember(MemberEntity memberEntity){
    public MemberEntity saveMember(MemberSignupRequestDto memberSignupRequestDto) {
        MemberEntity memberEntity = MemberEntity.createMemberEntity(memberSignupRequestDto, passwordEncoder);
        validateDuplicateMember(memberEntity);
        return memberRepository.save(memberEntity);
    }

    private void validateDuplicateMember(MemberEntity memberEntity) {
        MemberEntity findMember = memberRepository.findByEmail(memberEntity.getEmail());
        if (findMember != null) {
            throw new DuplicatedSignupException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if(memberEntity == null){
            System.out.println("유저 없음");
            throw new UsernameNotFoundException(email);
        }else{
            System.out.println("유저 찾음");
            System.out.println(memberEntity.getName());
        }
        return User.builder() // UserDetail을 구현하고 있는 User객체 반환
                .username(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .roles(memberEntity.getRole().toString())
                .build();
    }
}
