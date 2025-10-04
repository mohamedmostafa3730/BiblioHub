package com.portfolio.BiblioHub.book.dto;

import lombok.AllArgsConstructor;
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
    private Long categoryId;       // better use Long instead of Integer
    private String categoryName;

    // Authors (just names or mini DTOs)
    private List<String> authors;
}
