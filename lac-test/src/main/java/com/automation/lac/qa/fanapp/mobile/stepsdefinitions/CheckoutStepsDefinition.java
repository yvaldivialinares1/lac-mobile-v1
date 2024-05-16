package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile;
import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.questions.CheckOutScreenQuestions;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentMethodTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentPurchaseTask;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.CheckoutTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.ManageMyTicketsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.TransactionSuccessTasks;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;
import io.cucumber.java.en.And;
import java.util.List;

public class CheckoutStepsDefinition {
  private CheckoutTasks checkoutTasks = new CheckoutTasks();
  private TransactionSuccessTasks transactionSuccessTasks = new TransactionSuccessTasks();
  private ManageMyTicketsTasks manageMyTicketsTasks = new ManageMyTicketsTasks();
  private AddPaymentPurchaseTask addPaymentPurchaseTask =
    new AddPaymentPurchaseTask();

  private AddPaymentMethodTask addPaymentMethodTask =
    new AddPaymentMethodTask();

  protected CheckOutScreenQuestions checkOutScreenQuestions =
    new CheckOutScreenQuestions();

  protected CheckoutScreen checkoutScreen =
    new CheckoutScreen();

  /**
   * completes the ticket purchase process
   */
  @And("completes the ticket purchase process")
  public void completesTheTicketPurchaseProcess() {
    checkoutTasks.clickOnPlaceOrder();
    transactionSuccessTasks.clickOnContinue();
  }

  /**
   * Add a new payment card for ticket purchase
   */
  @And("the user adds a new payment method for the purchase of the ticket")
  public void theUserAddsNewPaymentCardForPurchase() {
    addPaymentPurchaseTask.addNewCardForPurchase();
    addPaymentMethodTask.addValidCard();
  }

  /**
   * Validates Added New Payment Card For Purchase in Checkout Screen
   */
  @And("the user validates the added new payment card for the purchase of the ticket")
  public void theUserValidatesAddedNewPaymentCardForPurchase() {
    List<PaymentMethodFile. PaymentMethod> cards = PaymentUtils.getAllUsedCards();
    DeviceActions.verticallyScrollToElement(checkoutScreen.getBtnPlaceOrder(),
      SwipeDirections.DOWN_TO_UP,1, 50);
    checkOutScreenQuestions.validatePaymentCardInCheckoutScreen(cards.get(cards.size() - 1));
  }
}