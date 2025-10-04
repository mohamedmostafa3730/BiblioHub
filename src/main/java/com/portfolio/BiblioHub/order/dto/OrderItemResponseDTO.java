package com.portfolio.BiblioHub.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {
    private Long orderItemId;
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private Double unitPrice;
}
