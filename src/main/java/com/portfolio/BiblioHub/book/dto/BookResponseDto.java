package com.portfolio.BiblioHub.book.dto;

import com.portfolio.BiblioHub.author.dto.AuthorResponseDto;
import com.portfolio.BiblioHub.category.dto.CategoryResponseDto;
import com.portfolio.BiblioHub.publisher.dto.PublisherResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Long bookId;
    private String bookTitle;
    private double bookPrice;
    private String bookISBN;
    private int stockQuantity;

    // Publisher details
    private Long publisherId;
    private String publisherName;

    // Category details
    private Integer categoryId;
    private String categoryName;

    // Author details (mini DTOs or just names)
    private List<String> authors;
}
