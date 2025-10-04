package com.portfolio.BiblioHub.order.service.impl;

import com.portfolio.BiblioHub.book.entity.Book;
import com.portfolio.BiblioHub.book.repository.BookRepository;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import com.portfolio.BiblioHub.customer.entity.Customer;
import com.portfolio.BiblioHub.customer.repository.CustomerRepository;
import com.portfolio.BiblioHub.order.dto.OrderItemRequestDTO;
import com.portfolio.BiblioHub.order.dto.OrderItemResponseDTO;
import com.portfolio.BiblioHub.order.dto.OrderRequestDto;
import com.portfolio.BiblioHub.order.dto.OrderResponseDto;
import com.portfolio.BiblioHub.order.entity.Order;
import com.portfolio.BiblioHub.order.entity.OrderItem;
import com.portfolio.BiblioHub.order.mapper.OrderItemMapper;
import com.portfolio.BiblioHub.order.mapper.OrderMapper;
import com.portfolio.BiblioHub.order.repository.OrderItemRepository;
import com.portfolio.BiblioHub.order.repository.OrderRepository;
import com.portfolio.BiblioHub.order.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RecordNotFoundException("Customer", dto.getCustomerId()));

        Order order = orderMapper.toEntity(dto);
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());

        // Save order first to get orderId for items
        Order savedOrder = orderRepository.save(order);

        List<OrderItem> items = dto.getOrderItems().stream().map(itemDto -> {
            Book book = bookRepository.findById(itemDto.getBookId())
                    .orElseThrow(() -> new RecordNotFoundException("Book", itemDto.getBookId()));

            OrderItem item = orderItemMapper.toEntity(itemDto);
            item.setOrder(savedOrder);
            item.setBook(book);
            return orderItemRepository.save(item);
        }).collect(Collectors.toList());

        savedOrder.setOrderItems(items);

        return orderMapper.toResponseDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Order", id));
        return orderMapper.toResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomer_CustomerId(customerId).stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateOrder(Long id, OrderRequestDto dto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Order", id));

        // Update main fields
        orderMapper.updateFromDto(dto, existingOrder);

        // Update items
        // Delete existing items first
        List<OrderItem> oldItems = orderItemRepository.findByOrder_OrderId(id);
        orderItemRepository.deleteAll(oldItems);

        // Add new items
        List<OrderItem> newItems = dto.getOrderItems().stream().map(itemDto -> {
            Book book = bookRepository.findById(itemDto.getBookId())
                    .orElseThrow(() -> new RecordNotFoundException("Book", itemDto.getBookId()));

            OrderItem item = orderItemMapper.toEntity(itemDto);
            item.setOrder(existingOrder);
            item.setBook(book);
            return orderItemRepository.save(item);
        }).collect(Collectors.toList());

        existingOrder.setOrderItems(newItems);

        Order updated = orderRepository.save(existingOrder);
        return orderMapper.toResponseDto(updated);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Order", id));
        orderItemRepository.deleteAll(order.getOrderItems());
        orderRepository.delete(order);
    }

    @Override
    public List<OrderResponseDto> addAllOrders(List<OrderRequestDto> dtos) {
        return dtos.stream()
                .map(this::createOrder) // reuse existing createOrder logic
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemResponseDTO> addAllOrderItems(List<OrderItemRequestDTO> dtos) {
        List<OrderItem> items = dtos.stream()
                .map(orderItemMapper::toEntity)
                .collect(Collectors.toList());

        List<OrderItem> saved = orderItemRepository.saveAll(items);

        return saved.stream()
                .map(orderItemMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
