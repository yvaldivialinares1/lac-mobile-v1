package com.automation.lac.qa.fanapp.mobile.enums;

import lombok.Getter;

@Getter
public enum BannerMessages {
  GAME_FACE_AND_AGE_SUCCESSFULLY_ADDED(
    "Your Game Face ID was successfully added and your age was verified.");
  private final String value;

  BannerMessages(String value) {
    this.value = value;
  }
}