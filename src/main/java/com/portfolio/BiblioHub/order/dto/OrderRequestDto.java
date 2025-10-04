package com.portfolio.BiblioHub.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Order status is required")
    @Size(min = 3, max = 30, message = "Order status must be between 3 and 30 characters")
    private String orderStatus;

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be greater than 0")
    private Double totalAmount;

    @NotNull(message = "Order items cannot be null")
    @Size(min = 1, message = "At least one order item is required")
    private List<OrderItemRequestDTO> orderItems;
}
