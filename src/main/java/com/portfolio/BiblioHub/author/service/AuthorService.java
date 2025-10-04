package com.portfolio.BiblioHub.author.service;

import com.portfolio.BiblioHub.author.dto.AuthorRequestDto;
import com.portfolio.BiblioHub.author.dto.AuthorResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorRequestDto dto);

    AuthorResponseDto getAuthorById(Long id);

    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto updateAuthor(Long id, AuthorRequestDto dto);

    void deleteAuthor(Long id);

    List<AuthorResponseDto> addAll(List<AuthorRequestDto> dtos);

}
