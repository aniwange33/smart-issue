package com.amos.silog.auth.dto;

import com.amos.silog.auth.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user data.
 * Contains user information that can be safely returned to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    /**
     * The user's ID
     */
    private Long id;
    
    /**
     * The user's full name
     */
    private String name;
    
    /**
     * The user's email address
     */
    private String email;
    
    /**
     * The user's role in the system
     */
    private UserRole role;
}