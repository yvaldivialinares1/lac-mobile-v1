package com.automation.lac.qa.onlinesim.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumerates the available countries to be used for OnlineSim API integration.
 */
@Getter
@RequiredArgsConstructor
public enum OnlineSimAvailableCountry {

  PORTUGAL("10950"),
  SLOVENIA("10950"),
  SWEDEN("10950");

  private final String senderCode;

  @Override
  public String toString() {
    return senderCode;
  }
}