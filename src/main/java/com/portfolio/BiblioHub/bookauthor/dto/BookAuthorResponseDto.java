package com.portfolio.BiblioHub.bookauthor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorResponseDto {
    private Long bookId;
    private String bookTitle;
    private Long authorId;
    private String authorName;
}
