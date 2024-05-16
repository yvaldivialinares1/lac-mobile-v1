package com.automation.lac.qa.fanapp.mobile.enums;

import lombok.Getter;

@Getter
public enum Month {
  JANUARY("January"),
  FEBRUARY("February"),
  MARCH("March"),
  APRIL("April"),
  MAY("May"),
  JUNE("June"),
  JULY("July"),
  AUGUST("August"),
  SEPTEMBER("September"),
  OCTOBER("October"),
  NOVEMBER("November"),
  DECEMBER("December");

  private final String description;

  Month(String description) {
    this.description = description;
  }

  public static String fromString(String month) {
    return Month.valueOf(month.toUpperCase()).getDescription();
  }
}
