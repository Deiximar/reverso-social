package com.reversosocial.config.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionType {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    VALIDATION(HttpStatus.BAD_REQUEST),
    BUSSINESS_LOGIC(HttpStatus.CONFLICT),
    GENERIC(HttpStatus.INTERNAL_SERVER_ERROR);

    public final HttpStatus httpStatus;

    private ExceptionType(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
