package com.automation.lac.qa.staffapp.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum defining file paths for various resources.
 */
@Getter
@RequiredArgsConstructor
public enum FilesPath {
  STAFF_CREDENTIALS_PATH("src/test/resources/test_data/staffapp/credentials.json"),
  PAYMENT_METHOD_PATH("src/test/resources/test_data/staffapp/paymentMethods.json");

  private final String text;


  @Override
  public String toString() {
    return text;
  }
}
