package com.shaastra.exceptions;


public class ContestNotFoundException extends RuntimeException {
    
    public ContestNotFoundException(String message) {
        super(message);
    }

    public ContestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
