package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates manual age verification options.
 */
@Getter
@AllArgsConstructor
public enum ManualAgeVerificationOption {
  CUSTOM_END_DATE("Custom end date"),
  END_OF_DAY("End of today"),
  NO_END_DATE("No end date");

  private final String label;
}
