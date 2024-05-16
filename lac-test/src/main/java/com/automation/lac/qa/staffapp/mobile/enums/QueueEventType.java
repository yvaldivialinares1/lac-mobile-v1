package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates types of events happening during access queue management.
 * Each event type relates to a specific queue category as:
 * Intuit Dome Access, Parking, Stores, Clubs.
 */
@Getter
@AllArgsConstructor
public enum QueueEventType {
  //Intuit Dome Access
  UNKNOWN_FAN("UNKNOWN FAN"),
  NO_TICKET("NO TICKET"),
  NEW_ISSUE("NEW ISSUE"),
  //Parking
  UNKNOWN_PLATE("UNKNOWN PLATE"),
  PLATE_NOT_PROPERLY_READ("PLATE NOT PROPERLY READ"),
  NO_PARKING_TICKET("NO PARKING TICKET"),
  INCORRECT_GARAGE("INCORRECT GARAGE"),
  //Stores
  ALCOHOL_NOT_PERMITTED("ALCOHOL NOT PERMITTED"),
  INVALID_PAYMENT_METHOD("INVALID PAYMENT METHOD"),
  ALCOHOL_LIMIT_REACHED("ALCOHOL LIMIT REACHED");

  private final String value;
}
