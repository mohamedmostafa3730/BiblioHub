package com.portfolio.BiblioHub.order.controller;

import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import com.portfolio.BiblioHub.order.dto.OrderItemRequestDTO;
import com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO;
import com.portfolio.BiblioHub.order.dto.OrderRequestDto;
import com.portfolio.BiblioHub.order.dto.OrderResponseDto;
import com.portfolio.BiblioHub.order.service.OrderService;
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

    // ================== ORDER ENDPOINTS ==================

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> addAllOrders(@RequestBody List<OrderRequestDto> dtos) {
        return responseBuilder.created(orderService.addAllOrders(dtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrder(@RequestBody OrderRequestDto dto) {
        return responseBuilder.created(orderService.createOrder(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> createOrdersBulk(@RequestBody List<OrderRequestDto> dtos) {
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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> updateOrder(@PathVariable Long id,
                                                                     @RequestBody OrderRequestDto dto) {
        return responseBuilder.ok(orderService.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return responseBuilder.noContent();
    }

    // ================== ORDER ITEM ENDPOINTS ==================

    @PostMapping("/items/bulk")
    public ResponseEntity<ApiResponse<List<OrderItemResponseDTO>>> addAllOrderItems(
            @RequestBody List<OrderItemRequestDTO> dtos) {
        return responseBuilder.created(orderService.addAllOrderItems(dtos));
    }

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<OrderItemResponseDTO>> createOrderItem(@RequestBody OrderItemRequestDTO dto) {
        return responseBuilder.created(orderService.createOrderItem(dto));
    }

    @PostMapping("/items/bulk")
    public ResponseEntity<ApiResponse<List<OrderItemResponseDTO>>> createOrderItemsBulk(@RequestBody List<OrderItemRequestDTO> dtos) {
        return responseBuilder.created(orderService.addAllOrderItems(dtos));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ApiResponse<OrderItemResponseDTO>> getOrderItem(@PathVariable Long id) {
        return responseBuilder.ok(orderService.getOrderItemById(id));
    }

    @GetMapping("/items")
    public ResponseEntity<ApiResponse<List<OrderItemResponseDTO>>> getAllOrderItems() {
        return responseBuilder.ok(orderService.getAllOrderItems());
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ApiResponse<OrderItemResponseDTO>> updateOrderItem(@PathVariable Long id,
                                                                             @RequestBody OrderItemRequestDTO dto) {
        return responseBuilder.ok(orderService.updateOrderItem(id, dto));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderService.deleteOrderItem(id);
        return responseBuilder.noContent();
    }
}
