package com.reversosocial.config.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {
  public UsernameNotFoundException(String message) {
    super(message);
  }
}