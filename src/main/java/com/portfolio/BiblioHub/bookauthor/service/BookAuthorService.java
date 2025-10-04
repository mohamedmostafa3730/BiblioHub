package com.portfolio.BiblioHub.bookauthor.service;

import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorRequestDto;
import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorResponseDto;

import java.util.List;

public interface BookAuthorService {
    BookAuthorResponseDto addAuthorToBook(BookAuthorRequestDto dto);

    void removeAuthorFromBook(Long bookId, Long authorId);

    List<BookAuthorResponseDto> getAuthorsByBook(Long bookId);

    List<BookAuthorResponseDto> getBooksByAuthor(Long authorId);

    List<BookAuthorResponseDto> addAll(List<BookAuthorRequestDto> dtos);

}
