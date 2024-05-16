package com.automation.lac.qa.utils;

/**
 * Custom exception class for application-specific runtime exceptions.
 */
public class CustomException extends RuntimeException {

  public CustomException(String message) {
    super(message);
  }

  public CustomException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomException(Throwable cause) {
    super(cause);
  }

}
