package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForListOfElementsIsNotEmpty;

import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfileInVenueXpScreen;
import io.qameta.allure.Step;

public class FanProfileInVenueXpQuestions extends FanProfileInVenueXpScreen {

  /**
   * Validate fan in venue xp displayed
   */
  @Step("Validate fan in venue xp screen displayed")
  public void validateIfFanTicketsDisplayed() {
    waitElementWillBeAvailable(getLblTicket(), 10);
    getSoftAssert().assertTrue(getLblTicket().isDisplayed(), "Tickets label");
    getSoftAssert().assertTrue(getBtnBuyATicket().isDisplayed(), "Button buy ticket");
    getSoftAssert().assertTrue(getBtnViewAll().isDisplayed(), "View all tickets button");
  }

  /**
   * Validate the default number of tickets
   */
  @Step("Validate default number tickets {defaultNumber}")
  public void areVisibleTickets(int defaultNumber) {
    getSoftAssert().assertEquals(getLstTicketCardComponents().size(), defaultNumber,
      "Tickets number");
  }

  /**
   * Validate if the ticket information banner is displayed
   */
  @Step("Validate ticket information visibility")
  public void isTicketInformationDisplayed() {
    getSoftAssert().assertTrue(getTicketInformationComponent().getLblTitle().isDisplayed(),
      "Ticket title");
    getSoftAssert().assertTrue(getTicketInformationComponent().getBtnClose().isDisplayed(),
      "Close button");
  }

  /**
   * Validate if the time filters are displayed
   */
  @Step("Validate time filters")
  public void areTimeFiltersDisplayed() {
    getSoftAssert().assertTrue(getTicketInformationComponent().getBtnPastTab().isDisplayed(),
      "Past tab");
    getSoftAssert().assertTrue(getTicketInformationComponent().getBtnUpcomingTab().isDisplayed(),
      "Upcoming tab");
  }

  public void areTypeOfEventFilterDisplayed() {
    getSoftAssert().assertFalse(getTicketInformationComponent().getLstFilterOptions().isEmpty(),
      "Filter options");
  }

  /**
   * Validate tickets in time past
   */
  @Step("Validate if the screen shows only past tickets")
  public void areOnlyTicketsInPastTime() {
    waitForListOfElementsIsNotEmpty(getTicketInformationComponent().getLstFilterOptions(), 30);
    getSoftAssert().assertFalse(
      getTicketInformationComponent().getLstTicketCardComponents().isEmpty(),
      "Tickets information");
  }

  /**
   * Validate if each ticket is of the filter type
   */
  @Step("Validate if the screen shows only tickets of type")
  public void areOnlyTicketsOfType() {
    waitForListOfElementsIsNotEmpty(getTicketInformationComponent().getLstFilterOptions(), 30);
    getSoftAssert().assertFalse(
      getTicketInformationComponent().getLstTicketCardComponents().isEmpty(),
      "Tickets information");
  }

  /**
   * Validate if purchased ticket is on the list
   */
  @Step("Validate if the screen shows only purchased tickets")
  public void validatePurchasedTicket() {
    getSoftAssert().assertTrue(getTicketInformationComponent().getLstTicketCardComponents().stream()
        .anyMatch(card -> card.getLblEventName().getText().equals(getTestContext().get("Ticket"))
          && card.getLblSeatsInfo().getText().equals(getTestContext().get("Seat"))),
      "Purchased ticket");
  }

  /**
   * Validate tickets in upcoming time
   */
  @Step("Validate if the screen shows only upcoming tickets")
  public void areOnlyTicketsInUpcomingTime() {
    waitForListOfElementsIsNotEmpty(getTicketInformationComponent().getLstFilterOptions(), 30);
    getSoftAssert().assertFalse(
      getTicketInformationComponent().getLstTicketCardComponents().isEmpty(),
      "Tickets information");
  }
}
