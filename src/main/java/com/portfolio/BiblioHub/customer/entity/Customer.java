package com.portfolio.BiblioHub.customer.entity;

import com.portfolio.BiblioHub.order.entity.Order;
import com.portfolio.BiblioHub.visitor.entity.Visitor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;


    @Column(name = "customer_user_name", unique = true, nullable = false, length = 50)
    private String customerUserName;

    @Column(name = "customer_password", nullable = false, length = 30)
    private String customerPassword;

    @Column(name = "customer_address", length = 400)
    private String customerAddress;

    @Column(name = "customer_phone", length = 20)
    private String customerPhone;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "visitor_id", unique = true)
    private Visitor visitor;

}
