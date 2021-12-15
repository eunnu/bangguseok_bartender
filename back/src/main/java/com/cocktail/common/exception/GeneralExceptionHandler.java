package com.cocktail.common.exception;

import com.cocktail.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler({NotFoundException.class, BusinessException.class, UnauthorizedException.class})
    public ResponseEntity<ResponseMessage> handle(CustomException ex) {
        return new ResponseEntity<>(new ResponseMessage(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ResponseMessage> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(new ResponseMessage(500, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
