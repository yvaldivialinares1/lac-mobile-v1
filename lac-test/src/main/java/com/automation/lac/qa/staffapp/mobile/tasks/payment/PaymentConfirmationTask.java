package com.automation.lac.qa.staffapp.mobile.tasks.payment;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.payment.PaymentConfirmationScreen;

public class PaymentConfirmationTask extends PaymentConfirmationScreen {

  public void clickOnManageTickets() {
    click(getBtnManageTickets(), "Manage tickets");
  }
}
