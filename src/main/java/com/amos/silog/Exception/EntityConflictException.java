package com.amos.silog.Exception;

public class EntityConflictException extends RuntimeException {
    public EntityConflictException(String entityName) {
        super(entityName + " was modified by another user. Please refresh.");
    }
}