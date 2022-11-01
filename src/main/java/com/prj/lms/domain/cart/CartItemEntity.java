package com.prj.lms.domain.cart;

import com.prj.lms.domain.audit.BaseEntity;
import com.prj.lms.domain.item.ItemEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="dpw_cart_item")
public class CartItemEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private ItemEntity itemEntity;

    private int count;
}
