package com.portfolio.BiblioHub.visitor.controller;

import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import com.portfolio.BiblioHub.visitor.dto.VisitorRequestDto;
import com.portfolio.BiblioHub.visitor.dto.VisitorResponseDto;
import com.portfolio.BiblioHub.visitor.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;
    private final ResponseBuilder responseBuilder;

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<VisitorResponseDto>>> addAllVisitors(
            @RequestBody List<VisitorRequestDto> dtos) {
        return responseBuilder.created(visitorService.addAll(dtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VisitorResponseDto>> createVisitor(@RequestBody VisitorRequestDto dto) {
        return responseBuilder.created(visitorService.createVisitor(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<VisitorResponseDto>>> createVisitorsBulk(@RequestBody List<VisitorRequestDto> dtos) {
        return responseBuilder.created(visitorService.addAll(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VisitorResponseDto>> getVisitor(@PathVariable Long id) {
        return responseBuilder.ok(visitorService.getVisitorById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VisitorResponseDto>>> getAllVisitors() {
        return responseBuilder.ok(visitorService.getAllVisitors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VisitorResponseDto>> updateVisitor(@PathVariable Long id,
                                                                         @RequestBody VisitorRequestDto dto) {
        return responseBuilder.ok(visitorService.updateVisitor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
        return responseBuilder.noContent();
    }
}
