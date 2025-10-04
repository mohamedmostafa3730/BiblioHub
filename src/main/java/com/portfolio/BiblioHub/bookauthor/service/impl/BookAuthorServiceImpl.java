package com.portfolio.BiblioHub.bookauthor.service.impl;

import com.portfolio.BiblioHub.author.entity.Author;
import com.portfolio.BiblioHub.author.repository.AuthorRepository;
import com.portfolio.BiblioHub.book.entity.Book;
import com.portfolio.BiblioHub.book.repository.BookRepository;
import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorRequestDto;
import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorResponseDto;
import com.portfolio.BiblioHub.bookauthor.entity.BookAuthor;
import com.portfolio.BiblioHub.bookauthor.entity.BookAuthorId;
import com.portfolio.BiblioHub.bookauthor.mapper.BookAuthorMapper;
import com.portfolio.BiblioHub.bookauthor.repository.BookAuthorRepository;
import com.portfolio.BiblioHub.bookauthor.service.BookAuthorService;
import com.portfolio.BiblioHub.common.exception.DuplicateResourceException;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookAuthorServiceImpl implements BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorMapper bookAuthorMapper;

    @Override
    public BookAuthorResponseDto addAuthorToBook(BookAuthorRequestDto dto) {
        // prevent duplicate relation
        if (bookAuthorRepository.existsByBook_BookIdAndAuthor_AuthorId(dto.getBookId(), dto.getAuthorId())) {
            throw new DuplicateResourceException("BookAuthor", "bookId & authorId", dto.getBookId() + ", " + dto.getAuthorId());
        }

        // fetch book and author
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RecordNotFoundException("Book", dto.getBookId()));
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RecordNotFoundException("Author", dto.getAuthorId()));

        // map DTO -> entity
        BookAuthor entity = bookAuthorMapper.toEntity(dto);
        entity.setBook(book);
        entity.setAuthor(author);

        BookAuthor saved = bookAuthorRepository.save(entity);
        return bookAuthorMapper.toResponseDto(saved);
    }

    @Override
    public void removeAuthorFromBook(Long bookId, Long authorId) {
        BookAuthorId id = new BookAuthorId();
        id.setBookId(bookId);
        id.setAuthorId(authorId);

        BookAuthor entity = bookAuthorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("BookAuthor relation", id));
        bookAuthorRepository.delete(entity);
    }

    @Override
    public List<BookAuthorResponseDto> getAuthorsByBook(Long bookId) {
        List<BookAuthor> relations = bookAuthorRepository.findByBook_BookId(bookId);
        if (relations.isEmpty()) {
            throw new RecordNotFoundException("Authors for Book", bookId);
        }
        return relations.stream()
                .map(bookAuthorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookAuthorResponseDto> getBooksByAuthor(Long authorId) {
        List<BookAuthor> relations = bookAuthorRepository.findByAuthor_AuthorId(authorId);
        if (relations.isEmpty()) {
            throw new RecordNotFoundException("Books for Author", authorId);
        }
        return relations.stream()
                .map(bookAuthorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookAuthorResponseDto> addAll(List<BookAuthorRequestDto> dtos) {
        List<BookAuthor> entities = dtos.stream()
                .map(bookAuthorMapper::toEntity)
                .collect(Collectors.toList());

        List<BookAuthor> saved = bookAuthorRepository.saveAll(entities);

        return saved.stream()
                .map(bookAuthorMapper::toResponseDto)
                .collect(Collectors.toList());

    }

}
