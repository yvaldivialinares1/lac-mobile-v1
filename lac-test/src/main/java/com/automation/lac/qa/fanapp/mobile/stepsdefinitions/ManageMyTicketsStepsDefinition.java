package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DO_NOT_ALLOW;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.mobile.AlertActions.isDeviceAlertDisplayed;
import static com.automation.lac.qa.utils.mobile.AlertActions.tapIosAlertOption;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.fanapp.mobile.questions.ManageMyTicketsQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.modals.AddBiometricsModalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.ManageMyTicketsTasks;
import io.cucumber.java.en.And;

public class ManageMyTicketsStepsDefinition {

  private final ManageMyTicketsQuestions manageMyTicketsQuestions = new ManageMyTicketsQuestions();
  private final ManageMyTicketsTasks manageMyTicketsTasks = new ManageMyTicketsTasks();
  private final AddBiometricsModalTask addBiometricsModalTask = new AddBiometricsModalTask();

  /**
   * completes the ticket purchase process
   */
  @And("the user validate the purchased ticket on manage my ticket screen")
  public void validateTickets() {
    if (!isAndroid() && isDeviceAlertDisplayed("Bluetooth")) {
      tapIosAlertOption(DO_NOT_ALLOW.getValue());
    }
    manageMyTicketsQuestions.validatePurchasedTicket();
    manageMyTicketsTasks.clickOnSkip();
  }

  /**
   * click on skip button on manage tickets screen and dismiss faster log in modal
   */
  @And("the user skip the manage your tickets screen")
  public void theUserSkipTheManageYourTicketsScreen() {
    manageMyTicketsTasks.clickOnSkip();
    if (isTheElementVisible(addBiometricsModalTask.getBtnIWillDoItLater(), 5)) {
      addBiometricsModalTask.clickOnIllDoItLater();
    }
  }
}