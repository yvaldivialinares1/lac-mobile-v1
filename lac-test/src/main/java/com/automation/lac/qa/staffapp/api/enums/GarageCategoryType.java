package com.automation.lac.qa.staffapp.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * enum describing parking access categories
 */
@Getter
@AllArgsConstructor
public enum GarageCategoryType {
  EAST_GARAGE("EAST GARAGE", "EAST_PARKING"),
  WEST_GARAGE("WEST GARAGE", "WEST_PARKING"),
  SOUTH_GARAGE("SOUTH GARAGE", "SOUTH_PARKING");

  private final String garage;
  private final String parking;
}
