package com.portfolio.BiblioHub.book.service.impl;

import com.portfolio.BiblioHub.author.entity.Author;
import com.portfolio.BiblioHub.author.repository.AuthorRepository;
import com.portfolio.BiblioHub.book.dto.BookRequestDto;
import com.portfolio.BiblioHub.book.dto.BookResponseDto;
import com.portfolio.BiblioHub.book.entity.Book;
import com.portfolio.BiblioHub.book.mapper.BookMapper;
import com.portfolio.BiblioHub.book.repository.BookRepository;
import com.portfolio.BiblioHub.book.service.BookService;
import com.portfolio.BiblioHub.category.entity.Category;
import com.portfolio.BiblioHub.category.repository.CategoryRepository;
import com.portfolio.BiblioHub.common.exception.DuplicateResourceException;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import com.portfolio.BiblioHub.publisher.entity.Publisher;
import com.portfolio.BiblioHub.publisher.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookResponseDto createBook(BookRequestDto dto) {
        // Check duplicate ISBN
        bookRepository.findByBookISBN(dto.getBookISBN())
                .ifPresent(b -> {
                    throw new DuplicateResourceException("Book", "ISBN", dto.getBookISBN());
                });

        // Convert DTO → Entity
        Book book = bookMapper.toEntity(dto);

        // Resolve publisher
        Publisher publisher = publisherRepository.findById(dto.getPublisherId())
                .orElseThrow(() -> new RecordNotFoundException("Publisher", dto.getPublisherId()));
        book.setPublisher(publisher);

        // Resolve category
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RecordNotFoundException("Category", dto.getCategoryId()));
        book.setCategory(category);

        // Resolve authors
        List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
        if (authors.isEmpty() && dto.getAuthorIds() != null && !dto.getAuthorIds().isEmpty()) {
            throw new RecordNotFoundException("Author(s)", dto.getAuthorIds());
        }
        book.setAuthors(authors);

        // Save
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(savedBook);
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Book", id));
        return bookMapper.toResponseDto(book);
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Book", id));

        // Check duplicate ISBN but allow same record
        bookRepository.findByBookISBN(dto.getBookISBN())
                .filter(b -> !Objects.equals(b.getBookId(), id))
                .ifPresent(b -> {
                    throw new DuplicateResourceException("Book", "ISBN", dto.getBookISBN());
                });

        // Update fields from DTO
        bookMapper.updateFromDto(dto, existingBook);

        // Update publisher
        Publisher publisher = publisherRepository.findById(dto.getPublisherId())
                .orElseThrow(() -> new RecordNotFoundException("Publisher", dto.getPublisherId()));
        existingBook.setPublisher(publisher);

        // Update category
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RecordNotFoundException("Category", dto.getCategoryId()));
        existingBook.setCategory(category);

        // Update authors
        List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
        if (authors.isEmpty() && dto.getAuthorIds() != null && !dto.getAuthorIds().isEmpty()) {
            throw new RecordNotFoundException("Author(s)", dto.getAuthorIds());
        }
        existingBook.setAuthors(authors);

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toResponseDto(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Book", id));
        bookRepository.delete(existingBook);
    }

    @Override
    public List<BookResponseDto> addAll(List<BookRequestDto> dtos) {
        List<Book> books = dtos.stream()
                .map(bookMapper::toEntity)
                .collect(Collectors.toList());

        List<Book> savedBooks = bookRepository.saveAll(books);

        return savedBooks.stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());

    }

}
