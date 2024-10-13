package com.reversosocial.config.exception;

public class ExistingUsernameException extends RuntimeException {
  public ExistingUsernameException(String message) {
    super(message);
  }
}
