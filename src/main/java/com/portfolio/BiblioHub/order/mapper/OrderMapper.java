package com.portfolio.BiblioHub.order.mapper;

import com.portfolio.BiblioHub.order.dto.OrderRequestDto;
import com.portfolio.BiblioHub.order.dto.OrderResponseDto;
import com.portfolio.BiblioHub.order.entity.Order;
import com.portfolio.BiblioHub.order.entity.OrderItem;
import com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrderItemMapper.class},
        imports = {java.util.stream.Collectors.class}
)
public interface OrderMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderRequestDto dto);

    @Mapping(target = "orderItems", ignore = true)
    void updateFromDto(OrderRequestDto dto, @MappingTarget Order entity);

    // Existing method
    @Mapping(target = "orderItems", expression = "java(mapOrderItems(order.getOrderItems()))")
    OrderResponseDto toResponseDto(Order order);

    default List<OrderItemResponseDTO> mapOrderItems(List<OrderItem> items) {
        if (items == null) return null;
        return items.stream()
                .map(item -> OrderItemResponseDTO.builder()
                        .orderItemId(item.getOrderItemId())
                        .bookId(item.getBook().getBookId())
                        .bookTitle(item.getBook().getBookTitle())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
