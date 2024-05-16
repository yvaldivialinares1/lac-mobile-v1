package com.automation.lac.qa.assertions;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testng.asserts.SoftAssert;

@Slf4j
@UtilityClass
public class SoftAssertManager {

  private static final ThreadLocal<CustomSoftAssert> softAssertThreadLocal = new ThreadLocal<>();

  /**
   * Retrieves the thread-local instance of CustomSoftAssert, creating it if it does not already
   * exist.
   * This ensures that each thread has its own instance of soft assertions to avoid conflicts in
   * parallel execution.
   *
   * @return The thread-local CustomSoftAssert instance.
   */
  public static CustomSoftAssert getSoftAssert() {
    CustomSoftAssert softAssert = softAssertThreadLocal.get();
    if (softAssert == null) {
      softAssert = new CustomSoftAssert();
      softAssertThreadLocal.set(softAssert);
    }
    return softAssert;
  }

  /**
   * Clears the thread-local storage of soft asserts.
   */
  public static void clearSoftAsserts() {
    softAssertThreadLocal.remove();
  }

  /**
   * Executes all collected soft assertions for the current thread.
   * If any assertions fail, an AssertionError will be thrown.
   * After execution, all soft asserts are cleared from the thread's storage.
   */
  public static void assertAll() {
    SoftAssert softAssert = softAssertThreadLocal.get();
    if (softAssert != null) {
      log.info("Verifying soft asserts in the scenario");
      softAssert.assertAll();
      clearSoftAsserts();
    }
  }
}
