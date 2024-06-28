package com.automation.lac.qa.fanapp.mobile.enums;

import lombok.Getter;

@Getter
public enum ParkingType {

  STANDARD("STND"),
  ACCESSIBLE("ADA"),
  ELECTRIC("ELEC");

  private final String value;

  ParkingType(String value) {
    this.value = value;
  }
}
