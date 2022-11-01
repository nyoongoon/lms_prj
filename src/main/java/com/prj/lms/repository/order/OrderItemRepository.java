package com.prj.lms.repository.order;

import com.prj.lms.domain.order.OrderEntity;
import com.prj.lms.domain.order.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findByOrderEntity(OrderEntity orderEntity);
}
