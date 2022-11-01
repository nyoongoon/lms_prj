package com.prj.lms.domain.member;

import com.prj.lms.domain.audit.BaseEntity;
import com.prj.lms.domain.member.memberConstant.Role;
import com.prj.lms.requestDto.member.MemberSignupRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="dpw_member")
@Getter @Setter
@ToString
public class MemberEntity extends BaseEntity {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    public static MemberEntity createMemberEntity(MemberSignupRequestDto memberSignupRequestDto
                                            , PasswordEncoder passwordEncoder){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(memberSignupRequestDto.getName());
        memberEntity.setEmail(memberSignupRequestDto.getEmail());
        memberEntity.setAddress(memberSignupRequestDto.getAddress());
        String password = passwordEncoder.encode(memberSignupRequestDto.getPassword());
        memberEntity.setPassword(password);
        memberEntity.setRole(Role.USER);
        return memberEntity;
    }

}
