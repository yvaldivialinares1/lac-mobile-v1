package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeOfPurchaseTicket {

  CLUB_PASS("club pass"),
  PARKING_PASS("parking pass"),
  REGULAR_TICKET("regular ticket");

  private final String label;
}
