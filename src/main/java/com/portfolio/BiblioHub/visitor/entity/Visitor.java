package com.portfolio.BiblioHub.visitor.entity;

import com.portfolio.BiblioHub.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "visitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id")
    private Long visitorId;

    @Column(name = "visitor_name", length = 50)
    private String visitorName;

    @Column(name = "visitor_email", length = 30, unique = true)
    private String visitorEmail;

    @OneToOne(mappedBy = "visitor")
    private Customer customer;

}
