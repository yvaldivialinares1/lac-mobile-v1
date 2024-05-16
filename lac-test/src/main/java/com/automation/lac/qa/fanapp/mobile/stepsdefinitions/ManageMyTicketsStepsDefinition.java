package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import com.automation.lac.qa.fanapp.mobile.questions.ManageMyTicketsQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.ManageMyTicketsTasks;
import io.cucumber.java.en.And;

public class ManageMyTicketsStepsDefinition {

  protected ManageMyTicketsQuestions manageMyTicketsQuestions = new ManageMyTicketsQuestions();

  protected ManageMyTicketsTasks manageMyTicketsTasks = new ManageMyTicketsTasks();

  /**
   * completes the ticket purchase process
   */
  @And("the user validate the purchased ticket on manage my ticket screen")
  public void validateTickets() {
    manageMyTicketsQuestions.validatePurchasedTicket();
    manageMyTicketsTasks.clickOnSkip();
  }

}