package com.automation.lac.qa.fanapp.mobile.enums;

import lombok.Getter;

/**
 * Enumeration defining the ticket availability
 */
@Getter
public enum TicketAvailability {
  AVAILABLE("Available", "AVAILABLE"),
  FEW_SEATS("Few seats", "FEW_SEATS"),
  FEW_SLOTS("Few slots", "FEW_SEATS"),
  SOLD_OUT("Sold Out", "SOLD_OUT");

  @Getter
  private final String title;

  @Getter
  private final String name;

  TicketAvailability(String title, String name) {
    this.title = title;
    this.name = name;
  }
}