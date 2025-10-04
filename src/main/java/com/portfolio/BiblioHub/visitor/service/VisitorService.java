package com.portfolio.BiblioHub.visitor.service;

import com.portfolio.BiblioHub.visitor.dto.VisitorRequestDto;
import com.portfolio.BiblioHub.visitor.dto.VisitorResponseDto;

import java.util.List;

public interface VisitorService {
    VisitorResponseDto createVisitor(VisitorRequestDto dto);

    VisitorResponseDto getVisitorById(Long id);

    List<VisitorResponseDto> getAllVisitors();

    VisitorResponseDto updateVisitor(Long id, VisitorRequestDto dto);

    void deleteVisitor(Long id);
    List<VisitorResponseDto> addAll(List<VisitorRequestDto> dtos);

}
