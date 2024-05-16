package com.automation.lac.qa.browserstack.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BrowserStackData {
  BS_API_CLOUD("https://api-cloud.browserstack.com/app-automate"),
  BS_SESSIONS("/sessions/");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}
