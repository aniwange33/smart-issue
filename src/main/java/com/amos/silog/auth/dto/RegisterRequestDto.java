package com.amos.silog.auth.dto;

import com.amos.silog.auth.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user registration requests.
 * Contains the data needed to register a new user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    /**
     * The user's full name
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * The user's email address
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    /**
     * The user's password (plain text)
     * Will be hashed before storage
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}