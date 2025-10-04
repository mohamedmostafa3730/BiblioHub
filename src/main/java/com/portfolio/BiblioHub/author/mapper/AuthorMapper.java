package com.portfolio.BiblioHub.author.mapper;

import com.portfolio.BiblioHub.author.dto.AuthorRequestDto;
import com.portfolio.BiblioHub.author.dto.AuthorResponseDto;
import com.portfolio.BiblioHub.author.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    // Convert Request DTO -> Entity
    Author toEntity(AuthorRequestDto dto);

    // Convert Entity -> Response DTO
    @Mapping(source = "authorId", target = "AuthorId")
    // because field names differ in case
    AuthorResponseDto toResponseDto(Author entity);

    // Update existing entity with data from DTO
    @Mapping(target = "authorId", ignore = true)   // don't overwrite primary key
    @Mapping(target = "books", ignore = true)
    // avoid overwriting relationships here
    void updateFromDto(AuthorRequestDto dto, @MappingTarget Author entity);
}
