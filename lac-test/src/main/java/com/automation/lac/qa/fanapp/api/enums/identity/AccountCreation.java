package com.automation.lac.qa.fanapp.api.enums.identity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountCreation {
  IDENTITY_NBA_URL("https://identity-dev.nba.com/dev/"),
  NBA_REGISTER("register"),
  NBA_UPDATE("profile");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}
