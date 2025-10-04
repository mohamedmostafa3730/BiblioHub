package com.portfolio.BiblioHub.visitor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorRequestDto {
    @NotBlank(message = "Visitor name is required")
    @Size(min = 2, max = 50, message = "Visitor name must be between 2 and 50 characters")
    private String visitorName;

    @NotBlank(message = "Visitor email is required")
    @Email(message = "Invalid email format")
    @Size(max = 30, message = "Email cannot exceed 30 characters")
    private String visitorEmail;
}
