package com.reversosocial.config.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(ExistingUsernameException.class)
  public ResponseEntity<ErrorObject> handleExistingUsernameException(ExistingUsernameException ex) {
    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.CONFLICT.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, ErrorObject>> handleRequestException(MethodArgumentNotValidException ex) {
    Map<String, ErrorObject> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      ErrorObject errorObject = new ErrorObject();
      errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
      errorObject.setMessage(error.getDefaultMessage());
      errorObject.setTimestamp(new Date());
      errors.put(error.getField(), errorObject);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorObject> handleInvalidDateFormat(HttpMessageNotReadableException ex) {
    ErrorObject errorObject = new ErrorObject();

    errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
    if (ex.getMessage().contains("LocalTime")) {
      errorObject.setMessage("La hora debe tener el formato HH:MM");
    } else if (ex.getMessage().contains("LocalDate")) {
      errorObject.setMessage("La fecha debe tener el formato yyyy-MM-dd");
    } else {
      errorObject.setMessage(ex.getMessage());
    }
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorObject> handleUsernameNotFoundException(UsernameNotFoundException ex) {
    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorObject> handleInvalidCredentialsException(InvalidCredentialsException ex) {
    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex) {

    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorObject> handleAccessDeniedException(AccessDeniedException ex) {
    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.FORBIDDEN.value());
    errorObject.setMessage("No tienes permiso para acceder a este recurso.");
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorObject> handleAuthenticationException(AuthenticationException ex) {
    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
    errorObject.setMessage("No estás autenticado. Por favor, inicia sesión.");
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorObject> handleCustomException(CustomException ex) {

    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.CONFLICT.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(EventFullException.class)
  public ResponseEntity<ErrorObject> handleEventFullException(EventFullException ex) {

    ErrorObject errorObject = new ErrorObject();
    errorObject.setStatusCode(HttpStatus.CONFLICT.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
  }

}
