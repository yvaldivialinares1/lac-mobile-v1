package com.automation.lac.qa.staffapp.mobile.questions.ticketing;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.staffapp.mobile.screens.ticketing.TicketingScreen;
import io.qameta.allure.Step;

public class TicketingQuestions extends TicketingScreen {

  /**
   * Validate if the ticketing screen is displayed
   */
  @Step("Validate ticketing screen is displayed")
  public void validateTicketingDisplayed() {
    getSoftAssert().assertTrue(getLblHeaderTitle().isDisplayed(), "Header title");
    getSoftAssert().assertTrue(getBtnAllEvents().isDisplayed(), "All events");
    getSoftAssert().assertTrue(getBtnSeason().isDisplayed(), "Season");
    getSoftAssert().assertFalse(getLstTicketsCards().isEmpty(), "List of cards of games or events");
    getSoftAssert().assertFalse(getLstTypesOfEvents().isEmpty(), "List of types of events");
    getSoftAssert().assertFalse(getLstEventsDates().isEmpty(), "List of dates of events");
  }

  /**
   * Validate if the purchase modal is displayed
   */
  @Step("Validate purchase modal is displayed")
  public void validatePurchaseModalDisplayed() {
    getSoftAssert().assertTrue(getPurchaseBannerComponent().getLblHeaderTitle().isDisplayed(),
      "Header title");
    getSoftAssert().assertTrue(getPurchaseBannerComponent().getLblTicketTitle().isDisplayed(),
      "Game or event title");
    getSoftAssert().assertTrue(getPurchaseBannerComponent().getLblTicketDate().isDisplayed(),
      "Game or event date");
    getSoftAssert().assertFalse(getPurchaseBannerComponent().getLstTypeTickets().isEmpty(),
      "Types of tickets");
  }
}
