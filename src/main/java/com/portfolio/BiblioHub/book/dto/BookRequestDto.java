package com.portfolio.BiblioHub.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
    @NotBlank(message = "The Book Title is required")
    private String bookTitle;

    @NotBlank(message = "The ISBN code for Book is required")
    private String BookISBN;

    @NotNull(message = "The price is required")
    @Positive(message = "The price must be greater than 0")
    private Double bookPrice;

    @NotNull(message = "The stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    private Long publisherId;
    private Long categoryId;
    private List<Long> authorIds;
}
