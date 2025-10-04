package com.portfolio.BiblioHub.book.entity;

import com.portfolio.BiblioHub.author.entity.Author;
import com.portfolio.BiblioHub.category.entity.Category;
import com.portfolio.BiblioHub.publisher.entity.Publisher;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_title", length = 50, nullable = false)
    private String bookTitle;

    @Column(name = "book_price", nullable = false)
    private double bookPrice;

    @Column(name = "book_isbn", nullable = false, unique = true, length = 30)
    private String bookISBN;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

}
