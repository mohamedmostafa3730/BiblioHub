package com.portfolio.BiblioHub.visitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorResponseDto {
    private Long visitorId;
    private String visitorName;
    private String visitorEmail;

    // Instead of sending full Customer, just send the ID
    private Long customerId;
}
