package com.startransport.errors;

public class UnknownEventProcessingException extends RuntimeException{
    public UnknownEventProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
