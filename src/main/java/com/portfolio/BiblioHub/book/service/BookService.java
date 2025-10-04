package com.portfolio.BiblioHub.book.service;

import com.portfolio.BiblioHub.book.dto.BookRequestDto;
import com.portfolio.BiblioHub.book.dto.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto createBook(BookRequestDto dto);

    BookResponseDto getBookById(Long id);

    List<BookResponseDto> getAllBooks();

    BookResponseDto updateBook(Long id, BookRequestDto dto);

    void deleteBook(Long id);

    List<BookResponseDto> addAll(List<BookRequestDto> dtos);

}
