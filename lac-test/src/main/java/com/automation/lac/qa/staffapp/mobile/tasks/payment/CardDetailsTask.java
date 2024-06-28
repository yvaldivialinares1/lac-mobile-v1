package com.automation.lac.qa.staffapp.mobile.tasks.payment;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.payment.CardDetailsScreen;

public class CardDetailsTask extends CardDetailsScreen {

  public void clickOnBackButton() {
    click(getBtnBack(), "Back option");
  }

  public void clickOnPreferredCheckBox() {
    click(getCheckboxPreferredCard(), "Preferred checkbox");
  }
}
