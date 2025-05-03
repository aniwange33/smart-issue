package com.amos.silog.Exception;


public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String message) {
       super(message);
    }
}
