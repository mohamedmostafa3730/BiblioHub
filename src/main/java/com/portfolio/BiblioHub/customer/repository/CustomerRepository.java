package com.portfolio.BiblioHub.customer.repository;

import com.portfolio.BiblioHub.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerUserName(String customerUserName);
}
