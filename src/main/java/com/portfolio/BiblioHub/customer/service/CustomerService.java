package com.portfolio.BiblioHub.customer.service;

import com.portfolio.BiblioHub.customer.dto.CustomerRequestDto;
import com.portfolio.BiblioHub.customer.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto dto);

    CustomerResponseDto getCustomerById(Long id);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto);

    void deleteCustomer(Long id);

    List<CustomerResponseDto> addAll(List<CustomerRequestDto> dtos);
}
