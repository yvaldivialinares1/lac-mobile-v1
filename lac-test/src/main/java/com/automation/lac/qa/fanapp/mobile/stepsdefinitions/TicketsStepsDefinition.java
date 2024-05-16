package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_ACCESSIBLE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.tasks.purchase.OrderDetailsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.SeatsAvailabilityTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.SeatsSelectionTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketDetailTask;
import io.cucumber.java.en.And;

public class TicketsStepsDefinition {
  private SeatsAvailabilityTask seatsAvailabilityTask = new SeatsAvailabilityTask();
  private OrderDetailsTasks orderDetailsTasks = new OrderDetailsTasks();
  private TicketDetailTask ticketDetailTask = new TicketDetailTask();
  private SeatsSelectionTasks seatsSelectionTasks = new SeatsSelectionTasks();

  /**
   * User selects the seat and choose accessible option if required
   */
  @And("the user selects seat to purchase")
  public void theUserSelectsSeat() {
    ticketDetailTask.clickOnFindTicketsButton();
    seatsSelectionTasks.updateSeatsToPurchase(
      getTestContext().getOrDefault(SELECTED_TICKET_COUNT, 1));
    if (getTestContext().getOrDefault(SELECTED_TICKET_ACCESSIBLE, "without")
      .equalsIgnoreCase("with"))
      seatsSelectionTasks.clickOnAccessibleSeatButton();
    ticketDetailTask.clickOnFindTicketsButton();
    seatsAvailabilityTask.selectSuggestedSeat(0);
    orderDetailsTasks.clickOnGoToPay();
  }
}