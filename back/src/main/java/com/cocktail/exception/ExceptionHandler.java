package com.cocktail.exception;

import com.cocktail.dto.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class, BusinessException.class})
    public ResponseEntity<ResponseMessage> handle(CustomException ex) {
        return new ResponseEntity<>(new ResponseMessage(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
    }

}
