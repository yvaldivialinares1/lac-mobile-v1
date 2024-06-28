package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.TransactionSuccessScreen;
import com.automation.lac.qa.utils.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;

@Slf4j
public class TransactionSuccessTasks extends TransactionSuccessScreen {

  /**
   * Click on Continue
   */
  public void clickOnContinue() {
    //TODO Remove when bug CA-54806 is fixed
    try {
      waitForElementVisibility(getBtnContinue(), 30);
      click(getBtnContinue(), CONTINUE.getValue());
    } catch (TimeoutException exception) {
      throw new CustomException("Payments fails in checkout screen.\nBug: CA-54806");
    }
  }
}