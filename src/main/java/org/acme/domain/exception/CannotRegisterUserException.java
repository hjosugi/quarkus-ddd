package org.acme.domain.exception;

public class CannotRegisterUserException extends RuntimeException {
  public CannotRegisterUserException(String m) {
    super(m);
  }
}