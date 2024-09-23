package com.reversosocial.config.exception;

public class RevSocException extends RuntimeException {

    private ExceptionType exceptionType;

    public RevSocException() {
        super();
    }

    public RevSocException(ExceptionType exceptionType, String message) {
        super(message);
        this.setExceptionType(exceptionType);
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

}
