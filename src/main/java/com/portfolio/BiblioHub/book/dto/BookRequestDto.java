package com.portfolio.BiblioHub.book.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "The Price of Book is required")
    private double bookPrice;

    @NotBlank(message = "The ISBN code for Book is required")
    private String BookISBN;

    @NotBlank(message = "The Quantity in Stock is Required")
    private int stockQuantity;

    private Long publisherId;
    private Long categoryId;
    private List<Long> authorIds;
}
