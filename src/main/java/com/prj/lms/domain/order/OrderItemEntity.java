package com.prj.lms.domain.order;

import com.prj.lms.domain.audit.BaseEntity;
import com.prj.lms.domain.item.ItemEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="dpw_order_item")
@Getter @Setter
public class OrderItemEntity extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    private int orderPrice; // 주문 가격
    private int count; //수량

}
