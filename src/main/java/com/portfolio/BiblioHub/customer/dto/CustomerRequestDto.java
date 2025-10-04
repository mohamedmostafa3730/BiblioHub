package com.portfolio.BiblioHub.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]{3,50}$",
            message = "Username can only contain letters, numbers, dots, underscores, and hyphens"
    )
    private String customerUserName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,30}$",
            message = "Password must contain at least one uppercase, one lowercase, one number, and one special character"
    )
    private String customerPassword;   // only in request, never in response

    @Size(max = 400, message = "Address cannot exceed 400 characters")
    private String customerAddress;

    @Pattern(
            regexp = "^[0-9+\\-\\s]{7,20}$",
            message = "Phone must be 7–20 characters and can include numbers, spaces, + or -"
    )
    private String customerPhone;

    private Long visitorId;
}