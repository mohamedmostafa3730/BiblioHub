package com.portfolio.BiblioHub.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private Long customerId;
    private String customerUserName;
    private String customerAddress;
    private String customerPhone;

    private Long visitorId; // just ID, not the full Visitor

    // Optional: summary of orders (not full entities)
    private List<Long> orderIds;
}
