package com.sparta.springcore.advice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String result;
    private String message;

    public RestException(HttpStatus httpStatus, String result, String message) {
        this.httpStatus = httpStatus;
        this.result = result;
        this.message = message;
    }
}
