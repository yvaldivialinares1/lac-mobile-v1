package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeOfTicket {

  ALL("All"),
  EVENTS("Events"),
  GAMES("Games"),
  PARKING("Parking");

  private final String value;
}
