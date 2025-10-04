package com.portfolio.BiblioHub.author.service.impl;

import com.portfolio.BiblioHub.author.dto.AuthorRequestDto;
import com.portfolio.BiblioHub.author.dto.AuthorResponseDto;
import com.portfolio.BiblioHub.author.entity.Author;
import com.portfolio.BiblioHub.author.mapper.AuthorMapper;
import com.portfolio.BiblioHub.author.repository.AuthorRepository;
import com.portfolio.BiblioHub.author.service.AuthorService;
import com.portfolio.BiblioHub.common.exception.DuplicateResourceException;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
        // check duplicate
        authorRepository.findByAuthorName(dto.getAuthorName())
                .ifPresent(a -> {
                    throw new DuplicateResourceException("Author", "name", dto.getAuthorName());
                });

        Author author = authorMapper.toEntity(dto);
        Author savedAuthor = authorRepository.save(author);

        return authorMapper.toResponseDto(savedAuthor);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Author", id));
        return authorMapper.toResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto dto) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Author", id));

        // check for duplicate name (but ignore if it’s the same record)
        authorRepository.findByAuthorName(dto.getAuthorName())
                .filter(a -> !a.getAuthorId().equals(id))
                .ifPresent(a -> {
                    throw new DuplicateResourceException("Author", "name", dto.getAuthorName());
                });

        authorMapper.updateFromDto(dto, existingAuthor);
        Author updatedAuthor = authorRepository.save(existingAuthor);

        return authorMapper.toResponseDto(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Author", id));
        authorRepository.delete(existingAuthor);
    }

    @Override
    public List<AuthorResponseDto> addAll(List<AuthorRequestDto> dtos) {
        List<Author> authors = dtos.stream()
                .map(authorMapper::toEntity)
                .collect(Collectors.toList());

        List<Author> savedAuthors = authorRepository.saveAll(authors);

        return savedAuthors.stream()
                .map(authorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
