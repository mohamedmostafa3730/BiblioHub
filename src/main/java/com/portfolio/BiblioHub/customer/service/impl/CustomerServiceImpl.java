package com.portfolio.BiblioHub.customer.service.impl;

import com.portfolio.BiblioHub.common.exception.DuplicateResourceException;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import com.portfolio.BiblioHub.customer.dto.CustomerRequestDto;
import com.portfolio.BiblioHub.customer.dto.CustomerResponseDto;
import com.portfolio.BiblioHub.customer.entity.Customer;
import com.portfolio.BiblioHub.customer.mapper.CustomerMapper;
import com.portfolio.BiblioHub.customer.repository.CustomerRepository;
import com.portfolio.BiblioHub.customer.service.CustomerService;
import com.portfolio.BiblioHub.visitor.entity.Visitor;
import com.portfolio.BiblioHub.visitor.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final VisitorRepository visitorRepository;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        // check duplicate username
        customerRepository.findByCustomerUserName(dto.getCustomerUserName())
                .ifPresent(c -> {
                    throw new DuplicateResourceException("Customer", "username", dto.getCustomerUserName());
                });

        Customer customer = customerMapper.toEntity(dto);

        // set Visitor if provided
        if (dto.getVisitorId() != null) {
            Visitor visitor = visitorRepository.findById(dto.getVisitorId())
                    .orElseThrow(() -> new RecordNotFoundException("Visitor", dto.getVisitorId()));
            customer.setVisitor(visitor);
        }

        Customer saved = customerRepository.save(customer);
        return customerMapper.toResponseDto(saved);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Customer", id));
        return customerMapper.toResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Customer", id));

        // check duplicate username
        customerRepository.findByCustomerUserName(dto.getCustomerUserName())
                .filter(c -> !Objects.equals(c.getCustomerId(), id))
                .ifPresent(c -> {
                    throw new DuplicateResourceException("Customer", "username", dto.getCustomerUserName());
                });

        customerMapper.updateFromDto(dto, existing);

        // update Visitor if provided
        if (dto.getVisitorId() != null) {
            Visitor visitor = visitorRepository.findById(dto.getVisitorId())
                    .orElseThrow(() -> new RecordNotFoundException("Visitor", dto.getVisitorId()));
            existing.setVisitor(visitor);
        }

        Customer updated = customerRepository.save(existing);
        return customerMapper.toResponseDto(updated);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Customer", id));
        customerRepository.delete(existing);
    }

    @Override
    public List<CustomerResponseDto> addAll(List<CustomerRequestDto> dtos) {
        List<Customer> customers = dtos.stream()
                .map(customerMapper::toEntity)
                .collect(Collectors.toList());

        List<Customer> saved = customerRepository.saveAll(customers);

        return saved.stream()
                .map(customerMapper::toResponseDto)
                .collect(Collectors.toList());

    }

}
