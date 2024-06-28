package com.automation.lac.qa.fanapp.mobile.tasks.paymentsmethod;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_PAYMENT_METHOD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_CARD_ADDED;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAttributeValue;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.AddPaymentMethodEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentMethodTask;
import io.qameta.allure.Step;

public class AddPaymentMethodEducationalTask extends AddPaymentMethodEducationalScreen {

  /**
   * Continue to Add Payment Method process
   */
  public AddPaymentMethodTask clickAddPaymentMethod() {
    waitElementWillBeAvailable(getBtnAddPaymentMethod(), 10);
    if (!isAndroid())
      waitForElementAttributeValue(getBtnAddPaymentMethod(), "visible", "true", 10);
    click(getBtnAddPaymentMethod(), ADD_PAYMENT_METHOD.getValue());
    return new AddPaymentMethodTask();
  }

  /**
   * Skip Add Payment Method Educational Screen
   */
  public void skipAddPaymentMethodEducationalScreen() {
    click(getBtnSkip(), SKIP.getValue());
  }

  @Step("The user add a payment method")
  public void completePaymentMethodRegistration() {
    clickAddPaymentMethod().addValidCard();
    getTestContext().set(IS_CARD_ADDED.name(), true);
  }

  @Step("The user skips add payment method")
  public void skipPaymentMethodsRegistration() {
    skipAddPaymentMethodEducationalScreen();
    getTestContext().set(IS_CARD_ADDED.name(), false);
  }
}