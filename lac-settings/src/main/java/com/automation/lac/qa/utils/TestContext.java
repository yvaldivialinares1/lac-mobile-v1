package com.automation.lac.qa.utils;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
  private final Map<String, Object> testData = new HashMap<>();

  /**
   * Sets a value in the test context with the specified key.
   * If the value is {@code null}, an exception will be thrown.
   *
   * @param key   the key to associate with the value.
   * @param value the value to store; cannot be {@code null}.
   * @throws IllegalArgumentException if the provided value is {@code null}.
   */
  public void set(String key, Object value) {
    if (value == null) {
      throw new CustomException("Value cannot be null");
    }
    testData.put(key, value);
  }

  /**
   * Retrieves the value associated with the specified key from the test context.
   * If the key does not exist, an exception will be thrown.
   *
   * @param key the key for which to retrieve the value.
   * @return the value associated with the specified key.
   * @throws IllegalArgumentException if the key is not present in the test context.
   */
  @SuppressWarnings("unchecked")
  public <T> T get(String key) {
    if (!testData.containsKey(key)) {
      throw new CustomException(
        "The specified key : " + key + " does not exist in the test context");
    }
    return (T) testData.get(key);
  }

  /**
   * Retrieves the value associated with the specified key from the test context.
   * If the key does not exist, it will return the default value.
   *
   * @param key the key for which to retrieve the value.
   * @return the value associated with the specified key or default value.
   */
  @SuppressWarnings("unchecked")
  public <T> T getOrDefault(String key, T defaultValue) {
    return (T) testData.getOrDefault(key, defaultValue);
  }

  /**
   * Checks if the test context contains a data entry associated with the specified key.
   * @param key the key whose presence in the test context is to be checked
   * @return {@code true} if the test context contains a mapping for the specified key,
   */
  public boolean containsKey(String key) {
    return testData.containsKey(key);
  }
}