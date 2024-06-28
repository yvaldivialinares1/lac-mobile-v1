package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForListOfElementsIsNotEmpty;

import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanTicketsScreen;
import io.qameta.allure.Step;

public class FanTicketsQuestions extends FanTicketsScreen {

  /**
   * Validate Fan ticket screen
   */
  public void isTicketInformationDisplayed() {
    waitForListOfElementsIsNotEmpty(getLstFilterOptions(), 30);
    getSoftAssert().assertTrue(getLblHeaderTitle().isDisplayed(), "Ticket title");
    getSoftAssert().assertTrue(getBtnBuyATicket().isDisplayed(), "Buy a ticket");
  }

  /**
   * Validate if the time filters are displayed
   */
  public void areTimeFiltersDisplayed() {
    waitForListOfElementsIsNotEmpty(getLstFilterOptions(), 30);
    getSoftAssert().assertTrue(getBtnPastTab().isDisplayed(), "Past tab");
    getSoftAssert().assertTrue(getBtnUpcomingTab().isDisplayed(), "Upcoming tab");
  }

  public void areTypeOfEventFilterDisplayed() {
    waitForListOfElementsIsNotEmpty(getLstFilterOptions(), 30);
    getSoftAssert().assertFalse(getLstFilterOptions().isEmpty(), "Filter options");
  }

  /**
   * Validate tickets in time past
   */
  public void areOnlyTicketsInPastTime() {
    waitForListOfElementsIsNotEmpty(getLstFilterOptions(), 30);
    getSoftAssert().assertFalse(getLstTicketCardComponents().isEmpty(), "Tickets information");
  }

  /**
   * Validate if each ticket is of the filter type
   */
  public void areOnlyTicketsOfType() {
    waitForListOfElementsIsNotEmpty(getLstFilterOptions(), 30);
    getSoftAssert().assertFalse(getLstTicketCardComponents().isEmpty(), "Tickets information");
  }

  /**
   * Validate tickets in upcoming time
   */
  @Step("Validate if the screen shows only upcoming tickets")
  public void areOnlyTicketsInUpcomingTime() {
    waitForListOfElementsIsNotEmpty(getLstFilterOptions(), 30);
    getSoftAssert().assertFalse(getLstTicketCardComponents().isEmpty(),
      "Tickets information");
  }
}
