package com.portfolio.BiblioHub.publisher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequestDto {

    @NotBlank(message = "Publisher name is required")
    @Size(min = 2, max = 50, message = "Publisher name must be between 2 and 50 characters")
    private String publisherName;

    @Size(max = 200, message = "Publisher address cannot exceed 200 characters")
    private String publisherAddress;

    @Size(max = 200, message = "Publisher contact info cannot exceed 200 characters")
    private String publisherContactInfo;
}
