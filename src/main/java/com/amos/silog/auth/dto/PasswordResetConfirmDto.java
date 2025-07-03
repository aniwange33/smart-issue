package com.amos.silog.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for password reset confirmation requests.
 * Contains the reset token and new password for completing a password reset.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetConfirmDto {
    
    /**
     * The password reset token sent to the user's email
     */
    @NotBlank(message = "Token is required")
    private String token;
    
    /**
     * The new password to set
     */
    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String newPassword;
}