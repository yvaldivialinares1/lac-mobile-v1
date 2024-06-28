package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FanProfileInformationOption {
  CLIPPER_BAND("Clipperband"),
  FAN_IDENTITY("Fan identity"),
  PAYMENTS_AND_SHARED_PAYMENTS("Payments & Shared payments"),
  REWARDS("Rewards"),
  TICKETS("Tickets"),
  VEHICLES("Vehicles");

  private final String value;
}
