package com.portfolio.BiblioHub.author.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDto {

    @NotBlank(message = "The Author Name is required")
    private String authorName;

    @NotBlank(message = "Please Enter Author profession or main role")
    private String authorBio;
}
