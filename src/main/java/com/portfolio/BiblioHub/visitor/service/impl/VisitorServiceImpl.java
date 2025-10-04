package com.portfolio.BiblioHub.visitor.service.impl;

import com.portfolio.BiblioHub.common.exception.DuplicateResourceException;
import com.portfolio.BiblioHub.common.exception.RecordNotFoundException;
import com.portfolio.BiblioHub.visitor.dto.VisitorRequestDto;
import com.portfolio.BiblioHub.visitor.dto.VisitorResponseDto;
import com.portfolio.BiblioHub.visitor.entity.Visitor;
import com.portfolio.BiblioHub.visitor.mapper.VisitorMapper;
import com.portfolio.BiblioHub.visitor.repository.VisitorRepository;
import com.portfolio.BiblioHub.visitor.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;

    @Override
    public VisitorResponseDto createVisitor(VisitorRequestDto dto) {
        // Check for duplicate email
        visitorRepository.findByVisitorEmail(dto.getVisitorEmail())
                .ifPresent(v -> {
                    throw new DuplicateResourceException("Visitor", "email", dto.getVisitorEmail());
                });

        Visitor visitor = visitorMapper.toEntity(dto);
        Visitor saved = visitorRepository.save(visitor);
        return visitorMapper.toResponseDto(saved);
    }

    @Override
    public VisitorResponseDto getVisitorById(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Visitor", id));
        return visitorMapper.toResponseDto(visitor);
    }

    @Override
    public List<VisitorResponseDto> getAllVisitors() {
        return visitorRepository.findAll()
                .stream()
                .map(visitorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public VisitorResponseDto updateVisitor(Long id, VisitorRequestDto dto) {
        Visitor existing = visitorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Visitor", id));

        // check duplicate email
        visitorRepository.findByVisitorEmail(dto.getVisitorEmail())
                .filter(v -> !Objects.equals(v.getVisitorId(), id))
                .ifPresent(v -> {
                    throw new DuplicateResourceException("Visitor", "email", dto.getVisitorEmail());
                });

        visitorMapper.updateFromDto(dto, existing);
        Visitor updated = visitorRepository.save(existing);
        return visitorMapper.toResponseDto(updated);
    }

    @Override
    public void deleteVisitor(Long id) {
        Visitor existing = visitorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Visitor", id));
        visitorRepository.delete(existing);
    }

    @Override
    public List<VisitorResponseDto> addAll(List<VisitorRequestDto> dtos) {
        List<Visitor> visitors = dtos.stream()
                .map(visitorMapper::toEntity)
                .collect(Collectors.toList());

        List<Visitor> saved = visitorRepository.saveAll(visitors);

        return saved.stream()
                .map(visitorMapper::toResponseDto)
                .collect(Collectors.toList());
    }


}
