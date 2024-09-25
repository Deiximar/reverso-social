package com.reversosocial.config.exception;


import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RevSocExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RevSocException.class)
    protected ResponseEntity<String> handleException(RevSocException exc, WebRequest  request) {

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
@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    FieldError fieldError = ex.getBindingResult().getFieldError();
    String message = (fieldError != null) ? fieldError.getDefaultMessage() : "Error de validación";
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
}
}
