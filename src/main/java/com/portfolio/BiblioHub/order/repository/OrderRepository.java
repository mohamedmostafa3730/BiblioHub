package com.portfolio.BiblioHub.order.repository;

import com.portfolio.BiblioHub.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Find all orders for a given customer
    List<Order> findByCustomer_CustomerId(Long customerId);

}
