package com.portfolio.BiblioHub.customer.controller;

import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import com.portfolio.BiblioHub.customer.dto.CustomerRequestDto;
import com.portfolio.BiblioHub.customer.dto.CustomerResponseDto;
import com.portfolio.BiblioHub.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ResponseBuilder responseBuilder;

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> addCustomersBulk(
            @Valid @RequestBody List<CustomerRequestDto> dtos) {
        return responseBuilder.created(customerService.addAll(dtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDto>> createCustomer(@RequestBody CustomerRequestDto dto) {
        return responseBuilder.created(customerService.createCustomer(dto));
    }

    @PostMapping("/bulk-create")
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> createCustomersBulk(@RequestBody List<CustomerRequestDto> dtos) {
        return responseBuilder.created(customerService.addAll(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getCustomer(@PathVariable Long id) {
        return responseBuilder.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> getAllCustomers() {
        return responseBuilder.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> updateCustomer(@PathVariable Long id,
                                                                           @RequestBody CustomerRequestDto dto) {
        return responseBuilder.ok(customerService.updateCustomer(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return responseBuilder.noContent();
    }
}
