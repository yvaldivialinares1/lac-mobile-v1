package com.automation.lac.qa.fanapp.api.enums.lacendpoints;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LacEndpoints {
  DELETE_ALL_PAYMENTS_METHODS("users/%s/payment-methods"),
  DELETE_LICENSE_PLATE("/identity/v1/intuit-dome-account/license-plate"),
  DELETE_TEAMMATE("/identity/v1/intuit-dome-account/mini/"),
  FIND_INTUIT_DOME_ACCOUNT_BY_ID("/identity/v1/intuit-dome-account/%s"),
  LOGIN("/identity/v2/intuit-dome-account/login"),
  PAYMENTS_V1_BASE_URI("payments/v1/");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}
