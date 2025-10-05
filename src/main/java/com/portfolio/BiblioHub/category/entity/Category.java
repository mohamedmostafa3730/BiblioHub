package com.portfolio.BiblioHub.category.entity;

import com.portfolio.BiblioHub.book.entity.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name", length = 20, nullable = false, unique = true)
    private String categoryName;

    @Column(name = "category_description", length = 220)
    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    private List<Book> books;
}
