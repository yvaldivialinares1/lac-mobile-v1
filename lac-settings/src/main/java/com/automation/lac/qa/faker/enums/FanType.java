package com.automation.lac.qa.faker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates types of fans with associated age ranges.
 * This enum defines different categories of fans based on age groups,
 * such as INFANT, CHILD, TEENAGER, and ADULT.
 * Each category has a minimum and maximum age associated with it.
 */
@Getter
@AllArgsConstructor
public enum FanType {
  MINI(2, 12), MINOR_ADULT(13, 17), YOUNG_ADULT(18, 20), ADULT(21, 100), ALL_ADULT(0, 0);
  private final int minAge;
  private final int maxAge;

  /**
   * Returns the FanType based on the given string representation.
   *
   * @param type The string representation of the FanType.
   * @return The corresponding FanType, or null if no matching type is found.
   */
  public static FanType fromString(String type) {
    if (type != null) {
      for (FanType fanType : FanType.values()) {
        if (type.equalsIgnoreCase(fanType.name())) {
          return fanType;
        }
      }
    }
    return null;
  }
}