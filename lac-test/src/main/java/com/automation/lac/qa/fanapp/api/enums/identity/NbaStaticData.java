package com.automation.lac.qa.fanapp.api.enums.identity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NbaStaticData {
  CHANNEL("NBA"),
  COUNTRY("US"),
  COUNTRY_UI("United States"),
  EMAIL_CONSENT("01"),
  EXTENSION("false"),
  LEAGUE("NBA"),
  LEAGUE_TEAM("LAC"),
  PHONE_DELIVERY_PERMISSION("true"),
  PLUS("+"),
  PRIVACY_CONSENT("1"),
  REGISTER_REASON("watch schedule"),
  REGISTER_REASON_CODE("2"),
  REGISTER_SOURCE("API"),
  STATE("CA"),
  STATE_UI("California"),
  TYPE("cellphone");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}
