package org.acme.domain.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String m) {
    super(m);
  }
}