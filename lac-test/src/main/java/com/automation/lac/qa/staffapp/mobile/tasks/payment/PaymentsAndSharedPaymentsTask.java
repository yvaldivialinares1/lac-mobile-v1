package com.automation.lac.qa.staffapp.mobile.tasks.payment;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.payment.PaymentsAndSharedPaymentsScreen;

public class PaymentsAndSharedPaymentsTask extends PaymentsAndSharedPaymentsScreen {

  public void clickOnPaymentsMethods() {
    click(getLstPaymentButtons().get(0), "Payment methods");
  }
}
