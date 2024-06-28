package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_IS_ACCESSIBLE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.questions.TicketListScreenQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.OrderDetailsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.TransactionSuccessTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.SeatsAvailabilityTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.SeatsSelectionTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketDetailTask;
import io.cucumber.java.en.And;

public class TicketsStepsDefinition {

  private final SeatsAvailabilityTask seatsAvailabilityTask = new SeatsAvailabilityTask();
  private final OrderDetailsTasks orderDetailsTasks = new OrderDetailsTasks();
  private final TicketDetailTask ticketDetailTask = new TicketDetailTask();
  private final SeatsSelectionTasks seatsSelectionTasks = new SeatsSelectionTasks();
  private final TransactionSuccessTasks transactionSuccessTasks = new TransactionSuccessTasks();
  private final TicketListScreenQuestions ticketListScreenQuestions =
    new TicketListScreenQuestions();

  /**
   * User selects the seat and choose accessible option if required
   */
  @And("the user selects seat to purchase")
  public void theUserSelectsSeat() {
    ticketDetailTask.clickOnFindTicketsButton();
    seatsSelectionTasks.updateSeatsToPurchase(
      getTestContext().getOrDefault(SELECTED_TICKET_COUNT, 1));
    if (getTestContext().getOrDefault(SELECTED_TICKET_IS_ACCESSIBLE, "without")
      .equalsIgnoreCase("with"))
      seatsSelectionTasks.clickOnAccessibleSeatButton();
    ticketDetailTask.clickOnFindSeatsButton();
    seatsAvailabilityTask.selectSuggestedSeat(0);
    orderDetailsTasks.waitForScreenToLoad();
  }

  /**
   * User proceeds to checkout
   */
  @And("the user proceeds to checkout")
  public void theUserProceedsToCheckout() {
    orderDetailsTasks.getNoOfTicketSection();
    orderDetailsTasks.clickOnGoToPay();
  }


  /**
   * User selects the continue button proceeds to Purchase
   */
  @And("the user selects continue button to purchase")
  public void theUserClicksOnContinueButtonProceedsToParkingOrderDetails() {
    transactionSuccessTasks.clickOnContinue();
  }

  /**
   * Validates that the Clippers games or events section is selected by default.
   *
   * @param ticketType The type of ticket section to check either "clippers games" or "events".
   */
  @And("^user should see (clippers games|events) section is selected by default")
  public void validateTicketSectionSelected(String ticketType) {
    if (ticketType.equalsIgnoreCase("clippers games"))
      ticketListScreenQuestions.clippersGamesSelected();
    else
      ticketListScreenQuestions.eventsSelected();
  }
}