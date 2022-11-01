package com.prj.lms.domain.cart;

import com.prj.lms.domain.audit.BaseEntity;
import com.prj.lms.domain.member.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "dpw_cart")
@Getter @Setter
@ToString
public class CartEntity extends BaseEntity {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

}
