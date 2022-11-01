package com.prj.lms.repository.order;

import com.prj.lms.domain.item.ItemEntity;
import com.prj.lms.domain.item.itemConstant.ItemSellStatus;
import com.prj.lms.domain.member.MemberEntity;
import com.prj.lms.domain.order.OrderEntity;
import com.prj.lms.domain.order.OrderItemEntity;
import com.prj.lms.repository.item.ItemRepository;
import com.prj.lms.repository.member.MemberRepository;
import com.prj.lms.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    public ItemEntity createItem(){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemNm("테스트 상품");
        itemEntity.setPrice(10000);
        itemEntity.setItemDetail("상세 설명");
        itemEntity.setItemSellStatus(ItemSellStatus.SELL);
        itemEntity.setStockNumber(100);
        itemEntity.setRegTime(LocalDateTime.now());
        itemEntity.setUpdateTime(LocalDateTime.now());
        return itemEntity;
    }

    public OrderEntity createOrder(int n){
        OrderEntity orderEntity = new OrderEntity();

        for (int i = 0; i < n; i++) {
            ItemEntity itemEntity = createItem();
            itemRepository.save(itemEntity);
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setItemEntity(itemEntity);
            orderItemEntity.setCount(10);
            orderItemEntity.setOrderPrice(1000);
            orderItemEntity.setOrderEntity(orderEntity);
            orderEntity.getOrderItemEntities().add(orderItemEntity);
        }

        MemberEntity memberEntity = new MemberEntity();
        memberRepository.save(memberEntity);

        orderEntity.setMemberEntity(memberEntity);
        orderRepository.save(orderEntity);
        return orderEntity;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        //given
        OrderEntity orderEntity = new OrderEntity();
        for (int i = 0; i < 3; i++) {
            ItemEntity itemEntity = this.createItem();
            ItemEntity savedItemEntity = itemRepository.save(itemEntity);
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setItemEntity(savedItemEntity);
            orderItemEntity.setCount(10);
            orderItemEntity.setOrderPrice(1000);
            orderItemEntity.setOrderEntity(orderEntity);
            orderEntity.getOrderItemEntities().add(orderItemEntity);
        }
        orderRepository.saveAndFlush(orderEntity);
        em.clear();
        //when
        OrderEntity savedOrderEntity = orderRepository.findById(orderEntity.getId())
                .orElseThrow(EntityNotFoundException::new);
        //then
        assertEquals(3, savedOrderEntity.getOrderItemEntities().size());
    }

    @Test
    @DisplayName("고아객체 제거 테스트 - orphanRemoval=true")
    public void orphanRemovalTest(){
        int n = 3;
        OrderEntity orderEntity = this.createOrder(n);
        orderEntity.getOrderItemEntities().remove(0);
        em.flush();
        List<OrderItemEntity> orderItemEntities = orderItemRepository.findByOrderEntity(orderEntity);
        assertEquals(orderItemEntities.size(), n - 1);
    }

    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest(){
        //given
        OrderEntity orderEntity = this.createOrder(3);
        Long orderItemId = orderEntity.getOrderItemEntities().get(0).getId();
        em.flush();
        em.clear();
        //when
        OrderItemEntity orderItemEntity = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("Order class : " + orderItemEntity.getOrderEntity().getClass());
        //then
    }
}