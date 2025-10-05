package com.portfolio.BiblioHub.book.controller;

import com.portfolio.BiblioHub.book.dto.BookRequestDto;
import com.portfolio.BiblioHub.book.dto.BookResponseDto;
import com.portfolio.BiblioHub.book.service.BookService;
import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ResponseBuilder responseBuilder;

    // Create a single book
    @PostMapping
    public ResponseEntity<ApiResponse<BookResponseDto>> createBook(
            @Valid @RequestBody BookRequestDto dto) {
        return responseBuilder.created(bookService.createBook(dto));
    }

    // Bulk create books
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> createBooksBulk(
            @Valid @RequestBody List<BookRequestDto> dtos) {
        return responseBuilder.created(bookService.addAll(dtos));
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> getBook(@PathVariable Long id) {
        return responseBuilder.ok(bookService.getBookById(id));
    }

    // Get all books
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> getAllBooks() {
        return responseBuilder.ok(bookService.getAllBooks());
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDto dto) {
        return responseBuilder.ok(bookService.updateBook(id, dto));
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return responseBuilder.noContent();
    }
}
