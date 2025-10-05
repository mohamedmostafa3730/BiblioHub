package com.portfolio.BiblioHub.category.service;

import com.portfolio.BiblioHub.category.dto.CategoryRequestDto;
import com.portfolio.BiblioHub.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto dto);

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto);

    void deleteCategory(Long id);

    List<CategoryResponseDto> addAll(List<CategoryRequestDto> dtos);

}
