package com.prj.lms.domain.order;

import com.prj.lms.domain.audit.BaseEntity;
import com.prj.lms.domain.member.MemberEntity;
import com.prj.lms.domain.order.orderConstant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="dpw_orders")
@Setter @Getter
public class OrderEntity extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    private LocalDateTime orderDate; //주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문 상태

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)//고아객체제거사용
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();

}
