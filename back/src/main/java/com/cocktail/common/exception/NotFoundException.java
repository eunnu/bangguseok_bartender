package com.cocktail.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super("Can't find resource");
    }

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
