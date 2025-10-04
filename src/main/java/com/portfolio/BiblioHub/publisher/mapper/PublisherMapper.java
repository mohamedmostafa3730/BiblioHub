package com.portfolio.BiblioHub.publisher.mapper;

import com.portfolio.BiblioHub.book.entity.Book;
import com.portfolio.BiblioHub.publisher.dto.PublisherRequestDto;
import com.portfolio.BiblioHub.publisher.dto.PublisherResponseDto;
import com.portfolio.BiblioHub.publisher.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for Publisher entity and DTOs.
 * Converts between:
 * - PublisherRequestDto -> Publisher
 * - Publisher -> PublisherResponseDto
 */
@Mapper(componentModel = "spring")
public interface PublisherMapper {
    /**
     * Converts a request DTO into a Publisher entity.
     * => Books are ignored here (handled in the Service layer).
     */
    @Mapping(target = "publisherId", ignore = true)
    @Mapping(target = "books", ignore = true)
    Publisher toEntity(PublisherRequestDto dto);

    /**
     * Converts a Publisher entity into a response DTO.
     * Maps list of Book entities into just their titles.
     */
    @Mapping(target = "books", expression = "java(mapBookTitles(publisher.getBooks()))")
    PublisherResponseDto toResponseDto(Publisher publisher);

    /**
     * Updates an existing Publisher entity from a request DTO.
     * => Books are not updated here.
     */
    @Mapping(target = "books", ignore = true)
    void updateFromDto(PublisherRequestDto dto, @MappingTarget Publisher entity);

    /**
     * Helper method to map a list of Book entities into a list of book titles.
     */
    default List<String> mapBookTitles(List<Book> books) {
        if (books == null) return null;
        return books.stream()
                .map(Book::getBookTitle)
                .collect(Collectors.toList());
    }

}
