package com.reversosocial.config.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
 @ExceptionHandler(ExistingEmailException.class)
  public ResponseEntity<ErrorObject> handleExistingEmailException(ExistingEmailException ex) {
    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.CONFLICT.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
  }
}
