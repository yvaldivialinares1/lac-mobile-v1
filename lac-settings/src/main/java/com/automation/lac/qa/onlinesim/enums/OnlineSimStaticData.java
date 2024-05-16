package com.automation.lac.qa.onlinesim.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumerates static data constants used for OnlineSim API integration.
 * Provides a centralized repository of keys and default values.
 */
@Getter
@RequiredArgsConstructor
public enum OnlineSimStaticData {

  ONLINE_SIM_BASE_URI("https://onlinesim.io/api/getFreeList"),
  ONLINE_SIM_COUNTRY("country"),
  ONLINE_SIM_NUMBER("number");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}
