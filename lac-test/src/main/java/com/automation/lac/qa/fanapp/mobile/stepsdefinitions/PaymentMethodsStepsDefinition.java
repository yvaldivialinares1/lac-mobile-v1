package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_CARD_ADDED;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.questions.CheckOutScreenQuestions;
import com.automation.lac.qa.fanapp.mobile.questions.MyPaymentsMethodScreenQuestion;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyPaymentsTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentMethodTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.MyPaymentMethodsTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentsmethod.AddPaymentMethodEducationalTask;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentMethodsStepsDefinition {
  private final AddPaymentMethodEducationalTask addPaymentMethodEducationalTask =
    new AddPaymentMethodEducationalTask();
  private final MyPaymentsMethodScreenQuestion myPaymentsMethodScreenQuestion =
    new MyPaymentsMethodScreenQuestion();
  private final CheckOutScreenQuestions checkOutScreenQuestions = new CheckOutScreenQuestions();
  private final MyPaymentMethodsTask myPaymentsMethodTask = new MyPaymentMethodsTask();

  private final AddPaymentMethodTask addPaymentMethodTask = new AddPaymentMethodTask();

  private final MyPaymentsTask myPaymentsTask = new MyPaymentsTask();

  @And("the user skip the payment method registration")
  public void theUserSkipThePaymentMethodRegistration() {
    addPaymentMethodEducationalTask.skipAddPaymentMethodEducationalScreen();
    getTestContext().set(IS_CARD_ADDED.name(), false);
  }

  @And("the user select payment method management from menu")
  public void theUserSelectPaymentMethodManagementFromMenu() {
    myPaymentsTask.clickOnPaymentMethodsManagement();
  }

  @And("^the user uses an existing payment method in (share payment method|checkout) screen")
  public void theUserSelectsExistingPaymentMethod(String screen) {
    checkOutScreenQuestions.validateExistingPaymentCard(screen);
  }

  /**
   * @param numberOfPaymentMethods number of payment methods to be added
   */
  @And("^the user adds (\\d+) valid payment methods( from educational)?$")
  public void theUserAddsValidPaymentMethods(int numberOfPaymentMethods, String screen) {
    if (screen != null && screen.contains("from educational"))
      addPaymentMethodEducationalTask.clickAddPaymentMethod();
    myPaymentsMethodTask.addValidPaymentMethods(numberOfPaymentMethods);
  }

  /**
   * @param numberOfPaymentMethods number of payment methods to be deleted
   */
  @And("the user deletes {int} payment methods")
  public void theUserDeletesPaymentMethods(int numberOfPaymentMethods) {
    myPaymentsMethodTask.deletePaymentMethods(numberOfPaymentMethods);
  }

  /**
   * Edits all payment methods used and set a nickname
   */
  @And("the user sets or edit a nickname for their existing payments method")
  public void theUserSetsOrEditANicknameForTheirExistingPaymentsMethod() {
    myPaymentsMethodTask.setNicknameForPaymentMethod(PaymentUtils.getUsedCard());
  }

  @And("the user validates the list of the payment cards in my profile")
  public void theUserVerifyThePaymentMethodsListInMyProfile() {
    myPaymentsMethodScreenQuestion.validatePaymentList();
  }

  /**
   * Completes adding payment method during account creation and sets a flag
   */
  @And("the user completes the payment method registration")
  public void theUserCompletesThePaymentMethodRegistration() {
    addPaymentMethodEducationalTask.clickAddPaymentMethod().addValidCard();
    getTestContext().set(IS_CARD_ADDED.name(), true);
  }

  @And("the user navigates to my profile from My payment methods")
  public void theUserNavigatesToMyProfileFromMyPaymentMethods() {
    myPaymentsMethodTask.goToMyProfileFromMyPayments();
  }

  @And("the user select share your payment methods from menu")
  public void theUserSelectShareYourPaymentMethodsFromMenu() {
    myPaymentsTask.clickOnShareYourPaymentMethods();
  }
}