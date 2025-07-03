package com.amos.silog.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for password reset requests.
 * Contains the email address of the user requesting a password reset.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetRequestDto {
    
    /**
     * The email address of the user requesting a password reset
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}