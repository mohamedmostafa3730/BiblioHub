package com.portfolio.BiblioHub.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private Long AuthorId;
    private String authorName;
    private String authorBio;
}
