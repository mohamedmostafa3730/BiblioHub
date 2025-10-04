package com.portfolio.BiblioHub.book.mapper;

import com.portfolio.BiblioHub.book.dto.BookRequestDto;
import com.portfolio.BiblioHub.book.dto.BookResponseDto;
import com.portfolio.BiblioHub.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between Book entities and DTOs
 * using MapStruct. This helps separate persistence layer objects (entities)
 * from API layer objects (DTOs).
 * Responsibilities:
 * - Convert request DTOs into Book entities for persistence.
 * - Convert Book entities into response DTOs for API output.
 * - Update existing Book entities from DTOs without overwriting key fields.
 */
@Mapper(componentModel = "spring")
public interface BookMapper {

    // Request DTO -> Entity
    @Mapping(target = "bookId", ignore = true) // don't set ID when creating
    @Mapping(target = "publisher", ignore = true) // will set manually in service
    @Mapping(target = "category", ignore = true)  // will set manually in service
    @Mapping(target = "authors", ignore = true)
    // will set manually in service
    Book toEntity(BookRequestDto dto);

    // Entity -> Response DTO
    @Mapping(source = "publisher.publisherId", target = "publisherId")
    @Mapping(source = "publisher.publisherName", target = "publisherName")
    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(target = "authors", expression = "java(book.getAuthors().stream().map(a -> a.getAuthorName()).collect(Collectors.toList()))")
    BookResponseDto toResponseDto(Book book);

    // Update entity from DTO (for PUT/PATCH)
    @Mapping(target = "bookId", ignore = true)    // don't overwrite PK
    @Mapping(target = "publisher", ignore = true) // update manually in service
    @Mapping(target = "category", ignore = true)  // update manually in service
    @Mapping(target = "authors", ignore = true)
    // update manually in service
    void updateFromDto(BookRequestDto dto, @MappingTarget Book book);

}
