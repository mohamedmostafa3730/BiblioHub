package com.portfolio.BiblioHub.publisher.service.impl;

import com.portfolio.BiblioHub.common.exception.DuplicateResourceException;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import com.portfolio.BiblioHub.publisher.dto.PublisherRequestDto;
import com.portfolio.BiblioHub.publisher.dto.PublisherResponseDto;
import com.portfolio.BiblioHub.publisher.entity.Publisher;
import com.portfolio.BiblioHub.publisher.mapper.PublisherMapper;
import com.portfolio.BiblioHub.publisher.repository.PublisherRepository;
import com.portfolio.BiblioHub.publisher.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public PublisherResponseDto createPublisher(PublisherRequestDto dto) {
        // check duplicate name
        publisherRepository.findByPublisherName(dto.getPublisherName())
                .ifPresent(p -> {
                    throw new DuplicateResourceException("Publisher", "name", dto.getPublisherName());
                });

        Publisher publisher = publisherMapper.toEntity(dto);
        Publisher saved = publisherRepository.save(publisher);
        return publisherMapper.toResponseDto(saved);
    }

    @Override
    public PublisherResponseDto getPublisherById(Integer id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Publisher", id));
        return publisherMapper.toResponseDto(publisher);
    }

    @Override
    public List<PublisherResponseDto> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherResponseDto updatePublisher(Integer id, PublisherRequestDto dto) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Publisher", id));

        // check duplicate name
        publisherRepository.findByPublisherName(dto.getPublisherName())
                .filter(p -> !Objects.equals(p.getPublisherId(), id))
                .ifPresent(p -> {
                    throw new DuplicateResourceException("Publisher", "name", dto.getPublisherName());
                });

        publisherMapper.updateFromDto(dto, existing);
        Publisher updated = publisherRepository.save(existing);
        return publisherMapper.toResponseDto(updated);
    }

    @Override
    public void deletePublisher(Integer id) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Publisher", id));
        publisherRepository.delete(existing);
    }

    @Override
    public List<PublisherResponseDto> addAll(List<PublisherRequestDto> dtos) {
        List<Publisher> publishers = dtos.stream()
                .map(publisherMapper::toEntity)
                .collect(Collectors.toList());

        List<Publisher> saved = publisherRepository.saveAll(publishers);

        return saved.stream()
                .map(publisherMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
