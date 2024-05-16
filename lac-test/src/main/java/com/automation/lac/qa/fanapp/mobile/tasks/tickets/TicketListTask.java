package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CLIPPERS_TABS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.EVENT_TABS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.TICKETS;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.TicketListScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import com.automation.lac.qa.fanapp.mobile.utils.TicketScreenUtils;
import java.util.List;
import org.openqa.selenium.WebElement;

public class TicketListTask extends TicketListScreen {

  /**
   * Scroll to Event from ticket list screen and click on ticket
   */
  public void scrollToTicketAndClick() {
    TicketType ticketType = getTestContext().get(SELECTED_TICKET_TYPE);
    long ticketTimeStamp = getTestContext().get(SELECTED_TICKET_TIMESTAMP);
    String date = TicketScreenUtils
      .convertTimestampFromApiToTicketScreenDate(ticketTimeStamp).toLowerCase();
    List<WebElement> tickets;
    if (ticketType.equals(TicketType.EVENT)) {
      tickets = getLstEventTickets();
    } else {
      tickets = getLstGameTickets();
    }
    WebElement ticket = DeviceActions.scrollIntoListToElementWithCondition(
      tickets, SwipeDirections.DOWN_TO_UP, -25,
      el -> TicketScreenUtils.extractTicketDateTime(ticketType, el.getText().toLowerCase()
          .replaceAll("p.m.", "pm").replaceAll("a.m.", "am"))
        .equals(date));
    click(ticket, TICKETS.getValue());
  }

  /**
   * click on Event Tabs
   */
  public void goToEventsTab() {
    click(getBtnEvents(), EVENT_TABS.getValue());
  }

  /**
   * click on Event Tabs
   */
  public void goToClippersTab() {
    click(getBtnClippers(), CLIPPERS_TABS.getValue());
  }

}
