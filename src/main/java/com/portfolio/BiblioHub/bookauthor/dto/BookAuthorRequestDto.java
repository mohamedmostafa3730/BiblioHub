package com.portfolio.BiblioHub.bookauthor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorRequestDto {

    @NotBlank(message = "The Book ID is required")
    private Long bookId;

    @NotBlank(message = "The Author ID is required")
    private Long authorId;

}
