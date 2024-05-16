package com.automation.lac.qa.magnifai.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration defining constants for MagnifAi data parameters.
 * These constants are used to represent fixed data values that are
 * frequently utilized across the MagnifAi testing framework.
 */
@Getter
@RequiredArgsConstructor
public enum MagnifAiData {
  FLEXIBLE_COMPARE_NAME("flexCompare"),
  FLEXIBLE_SEARCH_NAME("flexibleSearch"),
  MIN_SIMILARITY("75"),
  ATTACHMENT_NAME("Result_"),
  TEST_MODE("Automated"),
  IMAGE_FOLDER("src/test/resources/magnifai/fanapp/expected/"),
  SCREENSHOTS_FOLDER("src/test/resources/magnifai/fanapp/actual/"),
  FOUND("Found"),
  PASSED("Passed");

  private final String text;


  @Override
  public String toString() {
    return text;
  }
}
