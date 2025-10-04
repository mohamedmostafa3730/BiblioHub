package com.portfolio.BiblioHub.customer.mapper;

import com.portfolio.BiblioHub.customer.dto.CustomerRequestDto;
import com.portfolio.BiblioHub.customer.dto.CustomerResponseDto;
import com.portfolio.BiblioHub.customer.entity.Customer;
import com.portfolio.BiblioHub.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Customer entities and DTOs.
 * <p>
 * Responsibilities:
 * - Convert request DTOs into Customer entities for persistence.
 * - Convert Customer entities into response DTOs for API output.
 * - Update existing Customer entities from request DTOs.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    /*Notes:
     * - customerId is ignored (auto-generated).
     * - orders are ignored here, because orders should be added separately.
     * - visitor must be set in the Service layer (fetched by visitorId).*/
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "visitor", ignore = true)
    Customer toEntity(CustomerRequestDto dto);

    /*- visitorId is flattened (not the full Visitor entity).
     * - orders are mapped into a list of order IDs.
     * */
    @Mapping(target = "visitorId", source = "visitor.visitorId")
    @Mapping(target = "orderIds", expression = "java(mapOrders(customer.getOrders()))")
    CustomerResponseDto toResponseDto(Customer customer);

    /*Notes:
     * - customerId, orders, and visitor are not updated here.
     * - Only basic fields (username, password, address, phone) are updated.
     */
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "visitor", ignore = true)
    void updateFromDto(CustomerRequestDto dto, @MappingTarget Customer entity);

    /**
     * Helper method to extract order IDs from a list of Order entities.
     */

    default List<Long> mapOrders(List<Order> orders) {
        if (orders == null) return null;
        return orders.stream()
                .map(Order::getOrderId)
                .collect(Collectors.toList());
    }
}
