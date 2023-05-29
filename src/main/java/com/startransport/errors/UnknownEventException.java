package com.startransport.errors;

public class UnknownEventException extends Exception {
    public UnknownEventException() {
        super("unknown event type");
    }
}
