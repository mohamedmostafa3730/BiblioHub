package com.portfolio.BiblioHub.publisher.service;

import com.portfolio.BiblioHub.publisher.dto.PublisherRequestDto;
import com.portfolio.BiblioHub.publisher.dto.PublisherResponseDto;

import java.util.List;

public interface PublisherService {
    PublisherResponseDto createPublisher(PublisherRequestDto dto);

    PublisherResponseDto getPublisherById(Long id);

    List<PublisherResponseDto> getAllPublishers();

    PublisherResponseDto updatePublisher(Long id, PublisherRequestDto dto);

    void deletePublisher(Long id);

    List<PublisherResponseDto> addAll(List<PublisherRequestDto> dtos);

}
