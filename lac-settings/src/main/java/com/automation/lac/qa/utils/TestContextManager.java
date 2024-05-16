package com.automation.lac.qa.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestContextManager {
  private static final ThreadLocal<TestContext> testContext = new ThreadLocal<>();

  /**
   * Retrieves the current thread's {@code TestContext} instance, creating it if it does not exist.
   * This ensures a unique {@code TestContext} for each thread.
   *
   * @return The {@code TestContext} associated with the current thread.
   */
  public static TestContext getTestContext() {
    if (testContext.get() == null) {
      testContext.set(new TestContext());
    }
    return testContext.get();
  }

  /**
   * Clears the {@code TestContext} for the current thread.
   */
  public static void cleanTestContext() {
    testContext.remove();
  }
}
