package com.portfolio.BiblioHub.category.service.impl;

import com.portfolio.BiblioHub.category.dto.CategoryRequestDto;
import com.portfolio.BiblioHub.category.dto.CategoryResponseDto;
import com.portfolio.BiblioHub.category.entity.Category;
import com.portfolio.BiblioHub.category.mapper.CategoryMapper;
import com.portfolio.BiblioHub.category.repository.CategoryRepository;
import com.portfolio.BiblioHub.category.service.CategoryService;
import com.portfolio.BiblioHub.common.exception.DuplicateResourceException;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        // check duplicate name
        categoryRepository.findByCategoryName(dto.getCategoryName())
                .ifPresent(c -> {
                    throw new DuplicateResourceException("Category", "name", dto.getCategoryName());
                });

        Category category = categoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponseDto(saved);
    }

    @Override
    public CategoryResponseDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category", id));
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto updateCategory(Integer id, CategoryRequestDto dto) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category", id));

        // check duplicate name (ignore same record)
        categoryRepository.findByCategoryName(dto.getCategoryName())
                .filter(c -> !Objects.equals(c.getCategoryId(), id))
                .ifPresent(c -> {
                    throw new DuplicateResourceException("Category", "name", dto.getCategoryName());
                });

        categoryMapper.updateFromDto(dto, existing);
        Category updated = categoryRepository.save(existing);
        return categoryMapper.toResponseDto(updated);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category", id));
        categoryRepository.delete(existing);
    }

    @Override
    public List<CategoryResponseDto> addAll(List<CategoryRequestDto> dtos) {
        List<Category> categories = dtos.stream()
                .map(categoryMapper::toEntity)
                .collect(Collectors.toList());

        List<Category> saved = categoryRepository.saveAll(categories);

        return saved.stream()
                .map(categoryMapper::toResponseDto)
                .collect(Collectors.toList());

    }

}
