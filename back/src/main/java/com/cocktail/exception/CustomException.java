package com.cocktail.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
