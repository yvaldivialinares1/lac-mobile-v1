package com.automation.lac.qa.fanapp.mobile.enums;

import lombok.Getter;

/**
 * name to identify reminder card when using the steps
 * title to build locator when using string.format
 */
public enum ReminderNames {
  GAME_FACE_ID("Enroll your Game Face ID", "Game Face ID"),
  AGE_VERIFICATION("Verify your Age", "Age Verification"),
  ADD_CARDS("Add your cards", "Payment Method"),
  IDENTITY_PASS("Add identity Pass", "Identity Pass"),
  MINI_ACCOUNTS("Create Mini Accounts for your fellows fans", "Mini Accounts");

  @Getter
  private final String title;

  @Getter
  private final String name;

  ReminderNames(String title, String name) {
    this.title = title;
    this.name = name;
  }

  /**
   * This method returns ReminderNames enum based on the enum's name attribute
   * @param enumName name attribute
   * @return ReminderNames enum
   */
  public static ReminderNames getReminderCardEnum(String enumName) {
    for (ReminderNames reminderName : ReminderNames.values()) {
      if (reminderName.getName().equals(enumName)) {
        return reminderName;
      }
    }
    return null;
  }
}
