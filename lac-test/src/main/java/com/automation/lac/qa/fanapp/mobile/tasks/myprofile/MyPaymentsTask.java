package com.automation.lac.qa.fanapp.mobile.tasks.myprofile;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PAYMENT_HISTORY;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PAYMENT_METHODS_MANAGEMENT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SHARE_YOUR_PAYMENT_METHODS;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.fanapp.mobile.screens.commons.CommonsScreen;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.MyPaymentsScreen;

public class MyPaymentsTask extends MyPaymentsScreen {

  private final CommonsScreen commonsScreen = new CommonsScreen();

  public void goToMyProfileFromMyPayments() {
    click(waitForElementToBeClickable(5, commonsScreen.getBtnBack()), BACK.getValue());
  }

  public void clickOnPaymentMethodsManagement() {
    click(getBtnPaymentMethodManagement(), PAYMENT_METHODS_MANAGEMENT.getValue());
  }

  public void clickOnPaymentsHistory() {
    click(getBtnPaymentsHistory(), PAYMENT_HISTORY.getValue());
  }

  public void clickOnShareYourPaymentMethods() {
    click(getBtnSharePayments(), SHARE_YOUR_PAYMENT_METHODS.getValue());
  }

}