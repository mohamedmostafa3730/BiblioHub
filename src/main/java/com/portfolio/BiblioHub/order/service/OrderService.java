package com.portfolio.BiblioHub.order.service;

import com.portfolio.BiblioHub.order.dto.OrderItemRequestDTO;
import com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO;
import com.portfolio.BiblioHub.order.dto.OrderRequestDto;
import com.portfolio.BiblioHub.order.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto dto);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getAllOrders();

    List<OrderResponseDto> getOrdersByCustomer(Long customerId);

    OrderResponseDto updateOrder(Long id, OrderRequestDto dto);

    void deleteOrder(Long id);

    List<OrderResponseDto> addAllOrders(List<OrderRequestDto> dtos);

    List<OrderItemResponseDTO> addAllOrderItems(List<OrderItemRequestDTO> dtos);

    OrderItemResponseDTO createOrderItem(OrderItemRequestDTO dto);

    OrderItemResponseDTO getOrderItemById(Long id);

    List<OrderItemResponseDTO> getAllOrderItems();

    OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO dto);

    void deleteOrderItem(Long id);

}
