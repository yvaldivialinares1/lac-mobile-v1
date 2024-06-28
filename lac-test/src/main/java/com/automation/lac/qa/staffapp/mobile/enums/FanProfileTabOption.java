package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FanProfileTabOption {

  ACCESS("Access"),
  IN_VENUE_XP("In-Venue XP"),
  LOYALTY("Loyalty"),
  PAYMENTS("Payments");

  private final String value;
}
