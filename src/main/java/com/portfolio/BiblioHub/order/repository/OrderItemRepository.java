package com.portfolio.BiblioHub.order.repository;

import com.portfolio.BiblioHub.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Find all items for a given order
    List<OrderItem> findByOrder_OrderId(Long orderId);
}
