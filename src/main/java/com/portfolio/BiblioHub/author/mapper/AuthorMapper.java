package com.portfolio.BiblioHub.author.mapper;

import com.portfolio.BiblioHub.author.dto.AuthorRequestDto;
import com.portfolio.BiblioHub.author.dto.AuthorResponseDto;
import com.portfolio.BiblioHub.author.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/*Use unmappedTargetPolicy to control warnings/errors
    You can configure your mapper to ignore unmapped fields
    so that warnings don’t fail your build.*/
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthorMapper {

    Author toEntity(AuthorRequestDto dto);

    AuthorResponseDto toResponseDto(Author entity);

    @Mapping(target = "authorId", ignore = true)
    void updateFromDto(AuthorRequestDto dto, @MappingTarget Author entity);
}
