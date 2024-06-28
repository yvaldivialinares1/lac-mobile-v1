package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.payments;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;
import static com.automation.lac.qa.staffapp.mobile.enums.FanProfileInformationOption.PAYMENTS_AND_SHARED_PAYMENTS;
import static com.automation.lac.qa.staffapp.mobile.enums.FanProfileTabOption.PAYMENTS;

import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.FanProfilePaymentsQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.payment.AllPaymentsMethodsQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.payment.CardDetailsQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.payment.PaymentConfirmationQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.payment.PaymentsAndSharedPaymentsQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileInformationTask;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfilePaymentsTask;
import com.automation.lac.qa.staffapp.mobile.tasks.payment.AllPaymentMethodsTask;
import com.automation.lac.qa.staffapp.mobile.tasks.payment.CardDetailsTask;
import com.automation.lac.qa.staffapp.mobile.tasks.payment.PaymentsAndSharedPaymentsTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentsStepDefinitions {

  private final FanProfileInformationTask fanProfileInformationTask =
    new FanProfileInformationTask();
  private final PaymentConfirmationQuestions paymentQuestions = new PaymentConfirmationQuestions();
  private final FanProfilePaymentsTask fanProfilePaymentsTask = new FanProfilePaymentsTask();
  private final FanProfilePaymentsQuestions fanProfilePaymentsQuestions =
    new FanProfilePaymentsQuestions();
  private final PaymentsAndSharedPaymentsTask paymentsAndSharedPaymentsTask =
    new PaymentsAndSharedPaymentsTask();
  private final PaymentsAndSharedPaymentsQuestions paymentsAndSharedPaymentsQuestions =
    new PaymentsAndSharedPaymentsQuestions();
  private final AllPaymentMethodsTask allPaymentMethodsTask = new AllPaymentMethodsTask();
  private final AllPaymentsMethodsQuestions allPaymentsMethodsQuestions =
    new AllPaymentsMethodsQuestions();
  private final CardDetailsTask cardDetailsTask = new CardDetailsTask();
  private final CardDetailsQuestions cardDetailsQuestions = new CardDetailsQuestions();

  @Then("the confirmation screen with ticket information is displayed")
  public void theConfirmationScreenWithTicketInformationIsDisplayed() {
    paymentQuestions.validateSuccessfulPayment();
  }

  @And("is helping a Fan for review their payment methods")
  public void isHelpingAFanForReviewTheirPaymentMethods() {
    //TODO call API for validate available fan payment methods
    log.warn("Call the API for GET fan payment methods");
  }

  /**
   * Navigate to payment module and validate default payment methods
   */
  @When("the user goes to Payment module showing {int} payment methods")
  public void theUserGoesToPaymentModuleShowingNumberPaymentMethods(int paymentMethods) {
    if (isIpad()) {
      fanProfileInformationTask.getFanProfileTabOptionsComponent().clickOnTabOption(PAYMENTS);
      fanProfilePaymentsQuestions.validatePaymentsScreen(paymentMethods);
      fanProfilePaymentsQuestions.validateDefaultPaymentMethods(paymentMethods);
    } else {
      fanProfileInformationTask.clickOnFanOptionButton(PAYMENTS_AND_SHARED_PAYMENTS);
      paymentsAndSharedPaymentsQuestions.validatePaymentsScreen();
    }
  }

  /**
   * Navigate to all payment methods
   */
  @And("the user goes on the view all for search on all the payment methods {int}")
  public void theUserGoesOnTheViewAllForSearchOnAllThePaymentMethods(int paymentMethods) {
    if (isIpad()) {
      fanProfilePaymentsTask.clickOnViewAllButton();
      fanProfilePaymentsQuestions.validateAllPaymentsModal();
    } else {
      paymentsAndSharedPaymentsTask.clickOnPaymentsMethods();
      allPaymentsMethodsQuestions.validateAllPaymentMethods(paymentMethods);
    }
  }

  /**
   * Navigate to preferred card
   */
  @And("selects the preferred card showing the general information {int}")
  public void selectsThePreferredCardShowingTheGeneralInformation(int paymentMethods) {
    if (isIpad()) {
      fanProfilePaymentsTask.clickOnPreferredCard();
      fanProfilePaymentsQuestions.validateCard(true);
      fanProfilePaymentsTask.clickOnCloseCardDetails();
      fanProfilePaymentsQuestions.validateAllPaymentsModal();
    } else {
      allPaymentMethodsTask.clickOnPreferredCard();
      cardDetailsQuestions.validateCard(true);
      cardDetailsTask.clickOnBackButton();
      allPaymentsMethodsQuestions.validateAllPaymentMethods(paymentMethods);
    }
  }

  /**
   * Navigate to other card
   */
  @When("other card different to preferred card is selected shows the general information")
  public void otherCardDifferentToPreferredCardIsSelectedShowsTheGeneralInformation() {
    if (isIpad()) {
      fanProfilePaymentsTask.clickOnValidCard();
      fanProfilePaymentsQuestions.validateCard(false);
    } else {
      allPaymentMethodsTask.clickOnValidCard();
      cardDetailsQuestions.validateCard(false);
    }
  }

  /**
   * Validate preferred card
   */
  @Then("the card is marked as the preferred payment method")
  public void theCardIsMarkedAsThePreferredPaymentMethod() {
    if (isIpad()) {
      fanProfilePaymentsQuestions.isPreferredCheckBoxEmpty(true);
      fanProfilePaymentsTask.clickOnPreferredCheckBox();
      fanProfilePaymentsQuestions.isPreferredCheckBoxEmpty(false);
    } else {
      cardDetailsQuestions.isPreferredCheckBoxEmpty(true);
      cardDetailsTask.clickOnPreferredCheckBox();
      cardDetailsQuestions.isPreferredCheckBoxEmpty(false);
    }
  }

  @And("the user is helping a fan for add a new payment method")
  public void employeeIsHelpingAFanForAddANewPaymentMethod() {
    //TODO call API for add a new payment method, if is necessary
    log.warn("Call the API for POST fan payment method");
  }

  /**
   * Adding new payment method
   */
  @And("the user completes the adding card process {int}")
  public void theUserCompletesTheAddingCardProcess(int paymentMethods) {
    if (isIpad()) {
      fanProfilePaymentsTask.clickOnAddCard();
      fanProfilePaymentsQuestions.validateAddPaymentModal();
      fanProfilePaymentsTask.fillCardInformation();
      fanProfilePaymentsTask.clickOnAutofillData();
      fanProfilePaymentsQuestions.validateAddCardEnabled();
      fanProfilePaymentsTask.clickOnAddPaymentMethod();
    } else {
      paymentsAndSharedPaymentsTask.clickOnPaymentsMethods();
      allPaymentsMethodsQuestions.validateAllPaymentMethods(paymentMethods);
      allPaymentMethodsTask.clickOnAddCard();
      allPaymentsMethodsQuestions.validateAddPaymentModal();
      allPaymentMethodsTask.fillCardInformation();
      allPaymentMethodsTask.clickOnAutofillData();
      allPaymentsMethodsQuestions.validateAddCardEnabled();
      allPaymentMethodsTask.clickOnAddPaymentMethod();
    }
  }

  /**
   * Validate the confirmation message
   */
  @Then("the user sees a confirmation message")
  public void theUserSeesAConfirmationMessage() {
    if (isIpad()) {
      fanProfilePaymentsQuestions.validateSuccessMessage();
    } else {
      allPaymentsMethodsQuestions.validateSuccessMessage();
    }
  }

  /**
   * Validate the new payment method added
   */
  @And("the new payment method is added")
  public void theNewPaymentMethodIsAdded() {
    if (isIpad()) {
      fanProfilePaymentsQuestions.validateNewPaymentMethod();
    } else {
      allPaymentsMethodsQuestions.validateNewPaymentMethod();
    }
  }
}
