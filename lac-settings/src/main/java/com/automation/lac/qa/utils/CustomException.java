package com.automation.lac.qa.utils;

import static com.automation.lac.qa.allure.AllureLogger.attachScreenShot;
import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;

import io.qameta.allure.Allure;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * A custom exception class that extends {@link RuntimeException}.
 * This class is designed to handle custom exceptions in the application.
 *
 * <p>Note: Some changes in this class are implemented as workarounds to address
 * specific issues with BrowserStack.</p>
 */
@Slf4j
public class CustomException extends RuntimeException {

  private static final String NAME = "screenShoot";

  /**
   * Constructs a new {@code CustomException} with the specified detail message and cause.
   *
   * @param message The detail message, saved for later retrieval by the
   *                {@link #getMessage()} method.
   **/
  public CustomException(String message) {
    super(message);
    if (getDriver() != null) {
      attachScreenShot(NAME);
    }
  }

  /**
   * Constructs a new {@code CustomException} with the specified detail message and cause.
   *
   * @param message The detail message, saved for later retrieval by the
   *                {@link #getMessage()} method.
   * @param cause   The cause, saved for later retrieval by the {@link #getCause()} method.
   *                A {@code null} value is permitted and indicates that the cause is nonexistent
   *                or unknown.
   */
  public CustomException(String message, Throwable cause) {
    super(message, cause);
    log.error(cause.getMessage());
    if (getDriver() != null) {
      attachScreenShot(NAME);
      Allure.addAttachment("DOM Page Source", getDriver().getPageSource());
    }
    if (cause.getCause() != null) {
      log.error(cause.getCause().getMessage());
      Allure.addAttachment("Exception Cause", cause.getCause().getMessage());
    }
    Allure.addAttachment("Exception Message", cause.getMessage());
    Allure.addAttachment("Exception StackTrace", Arrays.toString(cause.getStackTrace()));
  }

  /**
   * Constructs a new {@code CustomException} with the specified detail message and cause.
   *
   * @param cause The cause, saved for later retrieval by the {@link #getCause()} method.
   *              A {@code null} value is permitted and indicates that the cause is nonexistent
   *              or unknown.
   */
  public CustomException(Throwable cause) {
    super(cause);
    log.error(cause.getMessage());
    if (cause.getCause() != null) {
      log.error(cause.getCause().getMessage());
      Allure.addAttachment("Exception Cause", cause.getCause().getMessage());
    }
    Allure.addAttachment("Exception Message", cause.getMessage());
    Allure.addAttachment("Exception StackTrace", Arrays.toString(cause.getStackTrace()));
  }

}