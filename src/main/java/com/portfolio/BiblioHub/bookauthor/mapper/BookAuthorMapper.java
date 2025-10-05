package com.portfolio.BiblioHub.bookauthor.mapper;

import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorRequestDto;
import com.portfolio.BiblioHub.bookauthor.dto.BookAuthorResponseDto;
import com.portfolio.BiblioHub.bookauthor.entity.BookAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between BookAuthor entity (join table)
 * and its DTOs.
 * <p>
 * BookAuthor links a Book with an Author using a composite key (BookAuthorId).
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BookAuthorMapper {
    /**
     * Converts a request DTO into a BookAuthor entity.
     * =>  Note: Only bookId and authorId are available in the request,
     * so the actual Book and Author entities must be set manually
     * in the Service layer after fetching them from repositories.
     */
    @Mapping(target = "id.bookId", source = "bookId")
    @Mapping(target = "id.authorId", source = "authorId")
    @Mapping(target = "book", ignore = true)   // will be set in service
    @Mapping(target = "author", ignore = true)
    // will be set in service
    BookAuthor toEntity(BookAuthorRequestDto dto);

    //Converts a BookAuthor entity into a response DTO
    @Mapping(source = "book.bookId", target = "bookId")
    @Mapping(source = "book.bookTitle", target = "bookTitle")
    @Mapping(source = "author.authorId", target = "authorId")
    @Mapping(source = "author.authorName", target = "authorName")
    BookAuthorResponseDto toResponseDto(BookAuthor entity);

    /**
     * Updates an existing BookAuthor entity with new IDs.
     * => Typically not used often, because join tables rarely get updated.
     */
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "author", ignore = true)
    void updateFromDto(BookAuthorRequestDto dto, @MappingTarget BookAuthor entity);
}
