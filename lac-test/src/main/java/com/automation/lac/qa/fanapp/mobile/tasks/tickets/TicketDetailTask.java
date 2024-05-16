package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.FIND_TICKETS;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketType.EVENT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.TicketDetailScreen;
import org.openqa.selenium.WebElement;

public class TicketDetailTask extends TicketDetailScreen {

  /**
   * Click on Find Tickets button Event
   */
  public void clickOnFindTicketsButton() {
    TicketType ticketType = getTestContext().get(SELECTED_TICKET_TYPE);
    WebElement findTicketsButton;
    if (ticketType.equals(EVENT))
      findTicketsButton = getBtnFindTicketsEvent();
    else
      findTicketsButton = getBtnFindTicketsGame();
    click(findTicketsButton, FIND_TICKETS.getValue());
  }
}
