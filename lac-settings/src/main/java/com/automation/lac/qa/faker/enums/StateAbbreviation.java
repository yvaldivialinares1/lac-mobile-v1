package com.automation.lac.qa.faker.enums;

import com.automation.lac.qa.utils.CustomException;
import lombok.Getter;

/**
 * Enumerates the U.S. states with their full names and corresponding abbreviations.
 * This enum provides a convenient way to access the two-letter abbreviations for each state.
 */
@Getter
public enum StateAbbreviation {
  ALABAMA("Alabama", "AL"),
  ALASKA("Alaska", "AK"),
  ARIZONA("Arizona", "AZ"),
  ARKANSAS("Arkansas", "AR"),
  CALIFORNIA("California", "CA"),
  COLORADO("Colorado", "CO"),
  CONNECTICUT("Connecticut", "CT"),
  DELAWARE("Delaware", "DE"),
  FLORIDA("Florida", "FL"),
  GEORGIA("Georgia", "GA"),
  HAWAII("Hawaii", "HI"),
  IDAHO("Idaho", "ID"),
  ILLINOIS("Illinois", "IL"),
  INDIANA("Indiana", "IN"),
  IOWA("Iowa", "IA"),
  KANSAS("Kansas", "KS"),
  KENTUCKY("Kentucky", "KY"),
  LOUISIANA("Louisiana", "LA"),
  MAINE("Maine", "ME"),
  MARYLAND("Maryland", "MD"),
  MASSACHUSETTS("Massachusetts", "MA"),
  MICHIGAN("Michigan", "MI"),
  MINNESOTA("Minnesota", "MN"),
  MISSISSIPPI("Mississippi", "MS"),
  MISSOURI("Missouri", "MO"),
  MONTANA("Montana", "MT"),
  NEBRASKA("Nebraska", "NE"),
  NEVADA("Nevada", "NV"),
  NEW_HAMPSHIRE("New Hampshire", "NH"),
  NEW_JERSEY("New Jersey", "NJ"),
  NEW_MEXICO("New Mexico", "NM"),
  NEW_YORK("New York", "NY"),
  NORTH_CAROLINA("North Carolina", "NC"),
  NORTH_DAKOTA("North Dakota", "ND"),
  OHIO("Ohio", "OH"),
  OKLAHOMA("Oklahoma", "OK"),
  OREGON("Oregon", "OR"),
  PENNSYLVANIA("Pennsylvania", "PA"),
  RHODE_ISLAND("Rhode Island", "RI"),
  SOUTH_CAROLINA("South Carolina", "SC"),
  SOUTH_DAKOTA("South Dakota", "SD"),
  TENNESSEE("Tennessee", "TN"),
  TEXAS("Texas", "TX"),
  UTAH("Utah", "UT"),
  VERMONT("Vermont", "VT"),
  VIRGINIA("Virginia", "VA"),
  WASHINGTON("Washington", "WA"),
  WEST_VIRGINIA("West Virginia", "WV"),
  WISCONSIN("Wisconsin", "WI"),
  WYOMING("Wyoming", "WY");

  private final String name;
  private final String abbreviation;

  /**
   * Constructs an instance of the enum with the full state name and its abbreviation.
   *
   * @param name         The full name of the state.
   * @param abbreviation The two-letter abbreviation of the state.
   */
  StateAbbreviation(String name, String abbreviation) {
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * Retrieves the state abbreviation by its full name.
   *
   * @param stateName The full name of the state for which the abbreviation is required.
   * @return The two-letter abbreviation of the state.
   * @throws IllegalArgumentException if the state name does not match any state.
   */
  public static String getAbbreviationByName(String stateName) {
    for (StateAbbreviation stateAbbreviation : StateAbbreviation.values()) {
      if (stateAbbreviation.getName().equalsIgnoreCase(stateName)) {
        return stateAbbreviation.getAbbreviation();
      }
    }
    throw new CustomException("No abbreviation found for state name: " + stateName);
  }
}
