package com.cocktail.common.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {

    private final static HttpStatus status = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException() {
        super("wrong permission error");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
