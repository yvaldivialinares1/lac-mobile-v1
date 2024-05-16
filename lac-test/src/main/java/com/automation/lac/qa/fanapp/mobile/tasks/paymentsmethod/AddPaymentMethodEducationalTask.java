package com.automation.lac.qa.fanapp.mobile.tasks.paymentsmethod;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_PAYMENT_METHOD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.AddPaymentMethodEducationalScreen;
import com.automation.lac.qa.utils.mobile.WaitActions;

public class AddPaymentMethodEducationalTask extends AddPaymentMethodEducationalScreen {

  /**
   * Continue to Add Payment Method process
   */
  public void clickAddPaymentMethod() {
    WaitActions.waitForElementAvailability(getBtnAddPaymentMethod(), 10);
    click(getBtnAddPaymentMethod(), ADD_PAYMENT_METHOD.getValue());
  }

  /**
   * Skip Add Payment Method Educational Screen
   */
  public void skipAddPaymentMethodEducationalScreen() {
    click(getBtnSkip(), SKIP.getValue());
  }
}
