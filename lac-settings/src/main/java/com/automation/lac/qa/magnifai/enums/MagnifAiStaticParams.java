package com.automation.lac.qa.magnifai.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration defining static parameters used within the MagnifAi API.
 * These constants represent commonly used parameter values that are
 * required for various API requests and configurations.
 */

@Getter
@RequiredArgsConstructor
public enum MagnifAiStaticParams {
  CLIENT_ID("aut-sdk"),
  IMG_PNG("image/png"),
  GRANT_TYPE("password"),
  CONNECTION("Connection"),
  KEEP_ALIVE("keep-alive");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}