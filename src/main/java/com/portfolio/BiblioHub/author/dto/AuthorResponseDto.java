package com.portfolio.BiblioHub.author.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private Long authorId;
    private String authorName;
    private String authorBio;
}
