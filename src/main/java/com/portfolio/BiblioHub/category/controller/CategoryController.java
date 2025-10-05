package com.portfolio.BiblioHub.category.controller;

import com.portfolio.BiblioHub.category.dto.CategoryRequestDto;
import com.portfolio.BiblioHub.category.dto.CategoryResponseDto;
import com.portfolio.BiblioHub.category.service.CategoryService;
import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ResponseBuilder responseBuilder;

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> addAllCategories(
            @RequestBody List<CategoryRequestDto> dtos) {
        return responseBuilder.created(categoryService.addAll(dtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDto>> create(@RequestBody CategoryRequestDto dto) {
        return responseBuilder.created(categoryService.createCategory(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> createBulk(@RequestBody List<CategoryRequestDto> dtos) {
        return responseBuilder.created(categoryService.addAll(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> get(@PathVariable Long id) {
        return responseBuilder.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAll() {
        return responseBuilder.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> update(@PathVariable Long id,
                                                                   @RequestBody CategoryRequestDto dto) {
        return responseBuilder.ok(categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return responseBuilder.noContent();
    }

}
