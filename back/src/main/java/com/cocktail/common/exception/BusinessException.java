package com.cocktail.common.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends CustomException {

    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public BusinessException() {
        super("internal server error");
    }

    public BusinessException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
