package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.FIND_SEATS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.FIND_TICKETS;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.tickets.TicketDetailScreen;

public class TicketDetailTask extends TicketDetailScreen {

  /**
   * Click on Find Seats button
   */
  public void clickOnFindSeatsButton() {
    click(getBtnFindSeats(), FIND_SEATS.getValue());
  }

  /**
   * Click on Find Tickets button
   */
  public void clickOnFindTicketsButton() {
    click(getBtnFindTickets(), FIND_TICKETS.getValue());
  }
}
