package com.portfolio.BiblioHub.category.mapper;

import com.portfolio.BiblioHub.book.entity.Book;
import com.portfolio.BiblioHub.category.dto.CategoryRequestDto;
import com.portfolio.BiblioHub.category.dto.CategoryResponseDto;
import com.portfolio.BiblioHub.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for Category entity and DTOs.
 * Converts between:
 * - CategoryRequestDto -> Category
 * - Category -> CategoryResponseDto
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    /**
     * Converts a request DTO into a Category entity.
     * Note: Book list is ignored here; handled in Service layer.
     */
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "books", ignore = true)
    Category toEntity(CategoryRequestDto dto);

    /**
     * Converts a Category entity into a response DTO.
     * Flattens the books into their titles only.
     */
    @Mapping(target = "books", expression = "java(mapBookTitles(category.getBooks()))")
    CategoryResponseDto toResponseDto(Category category);

    /**
     * Updates an existing Category entity from a request DTO.
     * Books list is not updated here.
     */
    @Mapping(target = "books", ignore = true)
    void updateFromDto(CategoryRequestDto dto, @MappingTarget Category entity);

    /**
     * Helper method to map list of Book entities to list of titles.
     */
    default List<String> mapBookTitles(List<Book> books) {
        if (books == null) return null;
        return books.stream()
                .map(Book::getBookTitle)
                .collect(Collectors.toList());
    }
}
