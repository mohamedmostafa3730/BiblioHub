package com.portfolio.BiblioHub.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
    @NotBlank(message = "Please, insert category Name is required")
    private String categoryName;
    @NotBlank(message = "Please, insert Description for this category")
    private String categoryDescription;
}
