package com.automation.lac.qa.staffapp.mobile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates navigation bar option tabs.
 */
@Getter
@AllArgsConstructor
public enum MyProfileMenuOptions {
  GAME_FACE_ID("GAME FACE ID"),
  LOGOUT("Logout"),
  MY_IDENTITY_PASS("MY IDENTITY PASS"),
  MY_VEHICLE("MY VEHICLE");

  private final String menuValue;
}
