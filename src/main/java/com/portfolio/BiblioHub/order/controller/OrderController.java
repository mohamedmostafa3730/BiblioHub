package com.portfolio.BiblioHub.order.controller;

import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import com.portfolio.BiblioHub.order.dto.OrderRequestDto;
import com.portfolio.BiblioHub.order.dto.OrderResponseDto;
import com.portfolio.BiblioHub.order.dto.OrderItemRequestDTO;
import com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO;
import com.portfolio.BiblioHub.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ResponseBuilder responseBuilder;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrder(
            @Valid @RequestBody OrderRequestDto dto) {
        return responseBuilder.created(orderService.createOrder(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> addOrdersBulk(
            @Valid @RequestBody List<OrderRequestDto> dtos) {
        return responseBuilder.created(orderService.addAllOrders(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrder(@PathVariable Long id) {
        return responseBuilder.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getAllOrders() {
        return responseBuilder.ok(orderService.getAllOrders());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getByCustomer(@PathVariable Long customerId) {
        return responseBuilder.ok(orderService.getOrdersByCustomer(customerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequestDto dto) {
        return responseBuilder.ok(orderService.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return responseBuilder.noContent();
    }

    // Item‐level endpoints:

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<OrderItemResponseDTO>> createOrderItem(
            @Valid @RequestBody OrderItemRequestDTO dto) {
        return responseBuilder.created(orderService.createOrderItem(dto));
    }

    @PostMapping("/items/bulk")
    public ResponseEntity<ApiResponse<List<OrderItemResponseDTO>>> addOrderItemsBulk(
            @Valid @RequestBody List<OrderItemRequestDTO> dtos) {
        return responseBuilder.created(orderService.addAllOrderItems(dtos));
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<OrderItemResponseDTO>> getOrderItem(
            @PathVariable Long itemId) {
        return responseBuilder.ok(orderService.getOrderItemById(itemId));
    }

    @GetMapping("/items")
    public ResponseEntity<ApiResponse<List<OrderItemResponseDTO>>> getAllOrderItems() {
        return responseBuilder.ok(orderService.getAllOrderItems());
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<OrderItemResponseDTO>> updateOrderItem(
            @PathVariable Long itemId,
            @Valid @RequestBody OrderItemRequestDTO dto) {
        return responseBuilder.ok(orderService.updateOrderItem(itemId, dto));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long itemId) {
        orderService.deleteOrderItem(itemId);
        return responseBuilder.noContent();
    }
}
