package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CLIPPERS_TABS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.EVENT_TABS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PARKING_TABS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.TICKETS;
import static com.automation.lac.qa.fanapp.mobile.utils.TicketScreenUtils.convertTimestampFromApiToTicketScreenDate;
import static com.automation.lac.qa.fanapp.mobile.utils.TicketScreenUtils.setDateFormatToPlatformFormat;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getTextFromElement;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;

import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.TicketListScreen;
import com.automation.lac.qa.utils.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class TicketListTask extends TicketListScreen {

  /**
   * Scroll to Event from ticket list screen and click on ticket
   */
  public void scrollToTicketAndClick() {
    TicketType ticketType = getTestContext().get(SELECTED_TICKET_TYPE);
    long ticketTimeStamp = getTestContext().get(SELECTED_TICKET_TIMESTAMP);
    String date = convertTimestampFromApiToTicketScreenDate(ticketTimeStamp);
    log.info("Expected event date: {}", date);
    WebElement ticketsScroll;
    WebElement ticketsType;
    switch (ticketType) {
      case EVENT -> {
        ticketsScroll = getScrollEventTicketView();
        ticketsType = getLstEventTickets().get(0);
      }
      case GAME -> {
        ticketsScroll = getScrollGameTicketView();
        ticketsType = getLstGameTickets().get(0);
      }
      case PARKING -> {
        ticketsScroll = getScrollParkingTicketView();
        ticketsType = getLstParkingPassesTickets().get(0);
      }
      default -> throw new CustomException("There is no option to select defined as " + ticketType);
    }
    date = setDateFormatToPlatformFormat(date, getTextFromElement(ticketsType, "label"));
    String platformXpath = isAndroid() ? getAndroidEventDateXpath() : getIosEventDateXpath();
    String eventDateXpath = String.format(platformXpath, date);
    swipeUntilFindElement(eventDateXpath, DOWN_TO_UP, ticketsScroll);
    WebElement eventToSelect = getElement(By.xpath(eventDateXpath));
    click(eventToSelect, TICKETS.getValue());
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

  /**
   * click on Parking Tabs
   */
  public void goToParkingTab() {
    click(getBtnParkingPasses(), PARKING_TABS.getValue());
  }
}
