package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationFilterOption {
  ALL("All"),
  SALES("Sales"),
  ACCESS("Access"),
  INCIDENTS("Incidents"),
  WAY_FINDING("Wayfinding"),
  FAN_SUPPORT("Fan Support");

  private final String name;
}
