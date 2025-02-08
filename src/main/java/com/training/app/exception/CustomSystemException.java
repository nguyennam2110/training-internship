package com.training.app.exception;

public class CustomSystemException extends RuntimeException {

  private final String message;

  public CustomSystemException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
