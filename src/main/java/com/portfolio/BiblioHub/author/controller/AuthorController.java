package com.portfolio.BiblioHub.author.controller;

import com.portfolio.BiblioHub.author.dto.AuthorRequestDto;
import com.portfolio.BiblioHub.author.dto.AuthorResponseDto;
import com.portfolio.BiblioHub.author.service.AuthorService;
import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import com.portfolio.BiblioHub.customer.dto.CustomerRequestDto;
import com.portfolio.BiblioHub.customer.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ResponseBuilder responseBuilder;

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<AuthorResponseDto>>> createAuthorsBulk(@Valid @RequestBody List<AuthorRequestDto> dtos) {
        return responseBuilder.created(authorService.addAll(dtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponseDto>> createAuthor(@RequestBody AuthorRequestDto dto) {
        return responseBuilder.created(authorService.createAuthor(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> getAuthor(@PathVariable Long id) {
        return responseBuilder.ok(authorService.getAuthorById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AuthorResponseDto>>> getAllAuthors() {
        return responseBuilder.ok(authorService.getAllAuthors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> updateAuthor(@PathVariable Long id,
                                                                       @RequestBody AuthorRequestDto dto) {
        return responseBuilder.ok(authorService.updateAuthor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return responseBuilder.noContent();
    }

}
