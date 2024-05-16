package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates navigation bar option tabs.
 */
@Getter
@AllArgsConstructor
public enum NavigationBarOption {
  HOME("Home"),
  SALES("Sales"),
  ACCESS("Access"),
  INCIDENTS("Incidents"),
  WAY_FINDING("Wayfinding"),
  MORE("More");

  private final String titleValue;
}
