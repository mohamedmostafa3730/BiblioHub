package com.portfolio.BiblioHub.bookauthor.controller;

import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorRequestDto;
import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorResponseDto;
import com.portfolio.BiblioHub.bookauthor.service.BookAuthorService;
import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-authors")
@RequiredArgsConstructor
public class BookAuthorController {

    private final BookAuthorService bookAuthorService;
    private final ResponseBuilder responseBuilder;

    // Add one author to a book
    @PostMapping
    public ResponseEntity<ApiResponse<BookAuthorResponseDto>> addAuthor(
            @Valid @RequestBody BookAuthorRequestDto dto) {
        return responseBuilder.created(bookAuthorService.addAuthorToBook(dto));
    }

    // Add multiple authors to books (bulk)
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<BookAuthorResponseDto>>> addBookAuthorBulk(
            @Valid @RequestBody List<BookAuthorRequestDto> dtos) {
        return responseBuilder.created(bookAuthorService.addAll(dtos));
    }

    // Remove an author from a book
    @DeleteMapping("/{bookId}/{authorId}")
    public ResponseEntity<Void> removeAuthor(@PathVariable Long bookId,
                                             @PathVariable Long authorId) {
        bookAuthorService.removeAuthorFromBook(bookId, authorId);
        return responseBuilder.noContent();
    }

    // Get all authors of a specific book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<ApiResponse<List<BookAuthorResponseDto>>> getAuthorsByBook(
            @PathVariable Long bookId) {
        return responseBuilder.ok(bookAuthorService.getAuthorsByBook(bookId));
    }

    // Get all books of a specific author
    @GetMapping("/author/{authorId}")
    public ResponseEntity<ApiResponse<List<BookAuthorResponseDto>>> getBooksByAuthor(
            @PathVariable Long authorId) {
        return responseBuilder.ok(bookAuthorService.getBooksByAuthor(authorId));
    }
}
