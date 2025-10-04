package com.portfolio.BiblioHub.bookauthor.repository;

import com.portfolio.BiblioHub.bookauthor.entity.BookAuthor;
import com.portfolio.BiblioHub.bookauthor.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
    // Find all authors for a book
    List<BookAuthor> findByBook_BookId(Long bookId);

    // Find all books for an author
    List<BookAuthor> findByAuthor_AuthorId(Long authorId);

    // Check if relation already exists (avoid duplicates)
    boolean existsByBook_BookIdAndAuthor_AuthorId(Long bookId, Long authorId);

}
