package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.api.models.PaymentMethodFile.PaymentMethod;
import static com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils.getAllUsedCards;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.questions.CheckOutScreenQuestions;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentMethodTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentPurchaseTask;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.CheckoutTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.TransactionSuccessTasks;
import io.cucumber.java.en.And;
import java.util.List;

public class CheckoutStepsDefinition {
  private final CheckoutTasks checkoutTasks = new CheckoutTasks();
  private final TransactionSuccessTasks transactionSuccessTasks = new TransactionSuccessTasks();
  private final AddPaymentPurchaseTask addPaymentPurchaseTask = new AddPaymentPurchaseTask();
  private final AddPaymentMethodTask addPaymentMethodTask = new AddPaymentMethodTask();
  private final CheckOutScreenQuestions checkOutScreenQuestions = new CheckOutScreenQuestions();
  private final CheckoutScreen checkoutScreen = new CheckoutScreen();

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
    List<PaymentMethod> cards = getAllUsedCards();
    waitForElementVisibility(checkoutScreen.getBtnCheckoutChange(), 20);
    checkOutScreenQuestions.validatePaymentCardInCheckoutScreen(cards.get(cards.size() - 1));
  }
}