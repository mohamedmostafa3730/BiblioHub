package com.portfolio.BiblioHub.bookauthor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorRequestDto {

    @NotNull(message = "The Book ID is required")
    private Long bookId;

    @NotNull(message = "The Author ID is required")
    private Long authorId;

}
