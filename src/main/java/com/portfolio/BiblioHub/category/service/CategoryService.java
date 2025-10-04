package com.portfolio.BiblioHub.category.service;

import com.portfolio.BiblioHub.category.dto.CategoryRequestDto;
import com.portfolio.BiblioHub.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto dto);

    CategoryResponseDto getCategoryById(Integer id);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Integer id, CategoryRequestDto dto);

    void deleteCategory(Integer id);

    List<CategoryResponseDto> addAll(List<CategoryRequestDto> dtos);

}
