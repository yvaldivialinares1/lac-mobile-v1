package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

  SUCCESS_AGE_VERIFICATION_ADDED("Age verification was successfully added."),
  SUCCESS_AGE_VERIFICATION_REMOVAL("Age verification was removed for this fan."),
  SUCCESS_PAYMENT("Payment successful"),
  SUCCESS_ADDED_PAYMENT_METHOD("Card added successfully. Continue with the payment"),
  SUCCESS_CHANGES_MADE("Changes were made successfully."),
  SUCCESS_CLIPPER_BAND_UNLINKED("Clipperband was successfully unlinked."),
  SUCCESS_INCIDENT_CREATION("Incident has been created successfully."),
  SUCCESS_VEHICLE_ADDED("Vehicle successfully added."),
  SUCCESS_VEHICLE_REMOVED("Vehicle successfully removed."),
  SUCCESS_VEHICLE_WAS_REMOVED("Vehicle was successfully removed.");

  private final String message;
}
