package com.amos.silog.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user login requests.
 * Contains the credentials needed to authenticate a user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    
    /**
     * The user's email address
     */
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    /**
     * The user's password
     */
    @NotNull(message = "Password is required")
    private String password;
}