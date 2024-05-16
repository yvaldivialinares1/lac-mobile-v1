package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SEARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.TICKET_SEARCH;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.fanapp.mobile.screens.tickets.TicketFilterScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;

public class TicketFilterTask extends TicketFilterScreen {

  /**
   * Search ticket from Search filter on ticket list screen
   *
   * @param ticketName String
   */
  public void searchTicket(String ticketName) {
    DeviceActions.waitForElementVisibility(getBtnSearchIcon(), 5);
    click(getBtnSearchIcon(), SEARCH.getValue());
    sendKeys(getTxtTicketSearch(), ticketName, TICKET_SEARCH.getValue());
    hideKeyboard("search");
  }
}