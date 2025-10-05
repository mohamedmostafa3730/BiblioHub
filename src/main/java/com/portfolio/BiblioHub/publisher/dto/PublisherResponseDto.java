package com.portfolio.BiblioHub.publisher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponseDto {
    private Long publisherId;
    private String publisherName;
    private String publisherAddress;
    private String publisherContactInfo;
    private List<String> books;
}
