package com.portfolio.BiblioHub.order.mapper;

import com.portfolio.BiblioHub.order.dto.OrderItemRequestDTO;
import com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO;
import com.portfolio.BiblioHub.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper for converting between OrderItem entities and DTOs.
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    /**
     * Converts a request DTO into an OrderItem entity.
     * => Book and Order must be set manually in Service layer.
     */
    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "book", ignore = true)
    OrderItem toEntity(OrderItemRequestDTO dto);

    /**
     * Converts an OrderItem entity into a response DTO.
     * Flattens book details.
     */
    @Mapping(source = "book.bookId", target = "bookId")
    @Mapping(source = "book.bookTitle", target = "bookTitle")
    OrderItemResponseDTO toResponseDto(OrderItem entity);

    /**
     * Update an existing OrderItem from request DTO.
     * Book and order are ignored (set in Service).
     */
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "book", ignore = true)
    void updateFromDto(OrderItemRequestDTO dto, @MappingTarget OrderItem entity);

}
