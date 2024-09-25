package com.reversosocial.config.exception;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException(String message) {
      super(message);
    }
  }
