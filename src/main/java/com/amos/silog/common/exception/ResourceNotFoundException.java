package com.amos.silog.common.exception;


public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String message) {
       super(message);
    }
}
