package com.portfolio.BiblioHub.order.mapper;

import com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO;
import com.portfolio.BiblioHub.order.dto.OrderRequestDto;
import com.portfolio.BiblioHub.order.dto.OrderResponseDto;
import com.portfolio.BiblioHub.order.entity.Order;
import com.portfolio.BiblioHub.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Order entities and DTOs.
 */
@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    /**
     * Converts a request DTO into an Order entity.
     * ⚠️ Customer must be set in Service layer.
     */
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orderDate", ignore = true) // usually set automatically
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderRequestDto dto);         // handled in Service

    /**
     * Converts an Order entity into a response DTO.
     * Flattens customer info and maps order items.
     */
    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "customer.customerUserName", target = "customerUserName")
    @Mapping(target = "orderItems", expression = "java(mapOrderItems(order.getOrderItems()))")
    OrderResponseDto toResponseDto(Order order);

    /**
     * Updates an existing Order entity from a request DTO.
     * Customer and items are ignored (set in Service).
     */
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    void updateFromDto(OrderRequestDto dto, @MappingTarget Order entity);

    /**
     * Helper method to convert a list of OrderItems to DTOs.
     */
    default List<OrderItemResponseDTO> mapOrderItems(List<OrderItem> items) {
        if (items == null) return null;
        return items.stream()
                .map(item -> com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO.builder()
                        .orderItemId(item.getOrderItemId())
                        .bookId(item.getBook().getBookId())
                        .bookTitle(item.getBook().getBookTitle())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
