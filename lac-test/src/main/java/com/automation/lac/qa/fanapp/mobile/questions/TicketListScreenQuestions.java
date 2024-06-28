package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.mobile.screens.tickets.TicketListScreen;

public class TicketListScreenQuestions extends TicketListScreen {

  /**
   * Asserts that the Clippers games section is selected in the tickets section.
   * For Android, checks if the button is selected using the {@code isSelected()} method.
   * For iOS, checks if the button's value attribute is "selected".
   */
  public void clippersGamesSelected() {
    if (isAndroid())
      getSoftAssert().assertTrue(getBtnClippers().isSelected(),
        "Clippers game is selected in tickets section");
    else
      getSoftAssert().assertTrue(
        getBtnClippers().getAttribute("value").equalsIgnoreCase("selected"),
        "Clippers game is selected in tickets section");
  }

  /**
   * Asserts that the Events section is selected in the tickets section.
   * For Android, checks if the button is selected using the {@code isSelected()} method.
   * For iOS, checks if the button's value attribute is "selected".
   */
  public void eventsSelected() {
    if (isAndroid())
      getSoftAssert().assertTrue(getBtnEvents().isSelected(),
        "Events is selected in tickets section");
    else
      getSoftAssert().assertTrue(
        getBtnEvents().getAttribute("value").equalsIgnoreCase("selected"),
        "Events is selected in tickets section");
  }
}
