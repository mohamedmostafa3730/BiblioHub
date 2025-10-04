package com.portfolio.BiblioHub.visitor.mapper;

import com.portfolio.BiblioHub.visitor.dto.VisitorRequestDto;
import com.portfolio.BiblioHub.visitor.dto.VisitorResponseDto;
import com.portfolio.BiblioHub.visitor.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper for Visitor entity and DTOs.
 * Handles:
 * - VisitorRequestDto -> Visitor
 * - Visitor -> VisitorResponseDto
 */
@Mapper(componentModel = "spring")
public interface VisitorMapper {
    /**
     * Converts a request DTO into a Visitor entity.
     * => Ignores the Customer (handled separately in service).
     */
    @Mapping(target = "visitorId", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Visitor toEntity(VisitorRequestDto dto);

    /**
     * Converts a Visitor entity into a response DTO.
     * Extracts the customerId from Customer (if present).
     */
    @Mapping(target = "customerId",
            expression = "java(visitor.getCustomer() != null ? visitor.getCustomer().getCustomerId() : null)")
    VisitorResponseDto toResponseDto(Visitor visitor);

    /**
     * Updates an existing Visitor entity from a request DTO.
     * => Customer is not updated here.
     */
    @Mapping(target = "customer", ignore = true)
    void updateFromDto(VisitorRequestDto dto, @MappingTarget Visitor entity);
}
