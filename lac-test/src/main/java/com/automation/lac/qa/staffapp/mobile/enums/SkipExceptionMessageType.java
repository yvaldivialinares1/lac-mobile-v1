package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SkipExceptionMessageType {
  IOS_PLATFORM_NOT_SUPPORTED("Some part of feature is not yet implemented for iOS platform");

  private final String message;
}
