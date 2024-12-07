package com.fantasy.packaging.backend.inventoryException;

import com.fantasy.packaging.backend.exception.MillAlreadyExistsException;
import com.fantasy.packaging.backend.exception.MillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An error occurred: " + e.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Request processing error: " + e.getMessage());
  }

  @ExceptionHandler(MillAlreadyExistsException.class)
  public ResponseEntity<String> handleMillAlreadyExistsException(MillAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }

  @ExceptionHandler(MillNotFoundException.class)
  public ResponseEntity<String> handleMillNotFoundException(MillNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }
}