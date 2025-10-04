package com.portfolio.BiblioHub.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long orderId;
    private Long customerId;
    private String customerUserName;

    private LocalDateTime orderDate;
    private String orderStatus;
    private Double totalAmount;

    private List<OrderItemResponseDTO> orderItems;
}
