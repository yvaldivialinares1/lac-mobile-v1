package com.automation.lac.qa.fanapp.mobile.enums;

import lombok.Getter;

@Getter
public enum LoginBannerMessage {
  EMAIL_EXIST_FOR_NBA("Email already exists for NBA. Please login with same password to proceed.");

  private final String value;

  LoginBannerMessage(String value) {
    this.value = value;
  }
}
