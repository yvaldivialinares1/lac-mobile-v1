package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_ROW;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_SECTION;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyLoggedProfileScreen;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.ManageMyTicketsScreen;
import com.automation.lac.qa.fanapp.mobile.utils.TicketScreenUtils;

public class ManageMyTicketsQuestions extends MyLoggedProfileScreen {

  protected ManageMyTicketsScreen manageMyTicketsScreen = new ManageMyTicketsScreen();

  /**
   * This method validates purchased ticket datetime, row and section on manage my ticket screen
   */
  public void validatePurchasedTicket() {
    //TODO : This implementation works for Android Only as of now as,
    //for iOS Dome structure is different for this screen
    String ticketDatetime = manageMyTicketsScreen.getTicketDateTimes()
      .getAttribute("content-desc");
    String ticketRowSection = manageMyTicketsScreen.getTicketRowSeatSection()
      .getAttribute("content-desc");

    getSoftAssert().assertEquals(ticketDatetime, TicketScreenUtils
      .convertTimestampFromApiToTicketScreenDate(getTestContext().get(SELECTED_TICKET_TIMESTAMP)));

    getSoftAssert().assertEquals(ticketRowSection.split("[|/]")[1].split("(?i)row")[1].trim(),
      getTestContext().get(SELECTED_TICKET_ROW));
    getSoftAssert().assertEquals(ticketRowSection.split("[|/]")[2].split("(?i)section")[1].trim(),
      getTestContext().get(SELECTED_TICKET_SECTION));
  }

}
