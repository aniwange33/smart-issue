package com.amos.silog.auth.model;

/**
 * Enum representing the possible roles a user can have in the system.
 */
public enum UserRole {
    /**
     * Regular user who can report issues
     */
    USER,
    
    /**
     * Developer who can resolve issues
     */
    DEVELOPER,
    
    /**
     * Administrator with full system access
     */
    ADMIN
}