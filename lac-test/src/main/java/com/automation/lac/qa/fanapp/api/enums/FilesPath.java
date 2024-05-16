package com.automation.lac.qa.fanapp.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum defining file paths for various resources.
 */
@Getter
@RequiredArgsConstructor
public enum FilesPath {
  CREDENTIALS_PATH("src/test/resources/test_data/fanapp/credentials.json"),
  PAYMENT_METHOD_PATH("src/test/resources/test_data/fanapp/paymentMethods.json");

  private final String text;


  @Override
  public String toString() {
    return text;
  }
}
