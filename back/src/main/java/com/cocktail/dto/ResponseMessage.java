package com.cocktail.dto;

import lombok.Data;

@Data
public class ResponseMessage {

    boolean success;
    Object response;
    Error error;

    public ResponseMessage(Object response) {
        this.response = response;
        this.success = true;
    }

    public ResponseMessage(int status, String message) {
        this.error = new Error(status, message);
        this.success = false;
    }

    @Data
    public class Error {
        int status;
        String message;

        public Error(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }


}
