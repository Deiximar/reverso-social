package com.reversosocial.config.exception;

import java.net.http.WebSocket;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RevSocExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RevSocException.class)
    protected ResponseEntity<String> handleException(RevSocException exc, WebSocket request) {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "¡Error desconocido!";

        if (Objects.nonNull(exc.getExceptionType())) {
            httpStatus = exc.getExceptionType().httpStatus;
        }
        if (StringUtils.isNotBlank(exc.getMessage())) {
            message = exc.getMessage();
        }

        return ResponseEntity.status(httpStatus).body(message);
    }

}
