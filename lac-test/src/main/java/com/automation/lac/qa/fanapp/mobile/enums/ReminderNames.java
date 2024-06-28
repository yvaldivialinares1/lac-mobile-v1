package com.automation.lac.qa.fanapp.mobile.enums;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * name to identify reminder card when using the steps
 * title to build locator when using string format
 */
@Getter
public enum ReminderNames {
  GAME_FACE_ID("Enroll your Game Face ID", "Game Face ID"),
  AGE_VERIFICATION("Verify your Age", "Age Verification"),
  ADD_CARDS("Add your cards", "Payment Method"),
  IDENTITY_PASS("Add Identity Pass", "Identity Pass"),
  MINI_ACCOUNTS("Create Mini Accounts for your fellows fans", "Mini Accounts"),
  SHARE_PAYMENT_METHOD("Share payment", "Share payment method"),
  SHARE_MINI_ACCOUNT("Share card with mini account", "Share cards with Teammate Accounts"),
  SHARE_FAN_ACCOUNT("Share card with fan account", "Share cards with a Fan Accounts");

  private final String title;

  private final String name;

  ReminderNames(String title, String name) {
    this.title = title;
    this.name = name;
  }

  /**
   * This method returns ReminderNames enum based on the enum's name attribute
   *
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

  /**
   * This method returns ReminderNames title strings in a list
   *
   * @return List of string with the titles of the enums
   */
  public static List<String> getTitlesReminderNames() {
    List<String> titlesReminderNames = new ArrayList<>();
    for (ReminderNames reminderName : ReminderNames.values()) {
      titlesReminderNames.add(reminderName.getTitle());
    }
    return titlesReminderNames;
  }
}
