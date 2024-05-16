package com.automation.lac.qa.putsbox.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumerates the endpoints for the PutsBox email service.
 * Each enumeration value represents a specific endpoint path.
 */
@Getter
@RequiredArgsConstructor
public enum PutsBoxEndPoints {

  GET_INSPECT("/inspect");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}
