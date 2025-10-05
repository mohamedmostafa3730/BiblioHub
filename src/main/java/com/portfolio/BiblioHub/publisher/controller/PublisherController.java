package com.portfolio.BiblioHub.publisher.controller;

import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import com.portfolio.BiblioHub.publisher.dto.PublisherRequestDto;
import com.portfolio.BiblioHub.publisher.dto.PublisherResponseDto;
import com.portfolio.BiblioHub.publisher.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;
    private final ResponseBuilder responseBuilder;

    @PostMapping
    public ResponseEntity<ApiResponse<PublisherResponseDto>> createPublisher(
            @Valid @RequestBody PublisherRequestDto dto) {
        return responseBuilder.created(publisherService.createPublisher(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<PublisherResponseDto>>> addPublishersBulk(
            @Valid @RequestBody List<PublisherRequestDto> dtos) {
        return responseBuilder.created(publisherService.addAll(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherResponseDto>> getPublisher(@PathVariable Long id) {
        return responseBuilder.ok(publisherService.getPublisherById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PublisherResponseDto>>> getAllPublishers() {
        return responseBuilder.ok(publisherService.getAllPublishers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherResponseDto>> updatePublisher(
            @PathVariable Long id,
            @Valid @RequestBody PublisherRequestDto dto) {
        return responseBuilder.ok(publisherService.updatePublisher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return responseBuilder.noContent();
    }
}
