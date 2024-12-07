package com.fantasy.packaging.backend.exception;

public class MillAlreadyExistsException extends RuntimeException {
  public MillAlreadyExistsException(String message) {
    super(message);
  }
}