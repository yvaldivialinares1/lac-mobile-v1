package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.fanapp.mobile.screens.sharepayments.SharePaymentsScreen;
import org.openqa.selenium.By;

public class SharePaymentMethodQuestions extends SharePaymentsScreen {

  /**
   * Validates if the preferred card is selected as an existing payment method.
   * Throws a CustomException if no card is added to purchase the ticket.
   */
  public void validateMiniAccountsToShareWith(String name) {
    String accountFullName =
      isAndroid()
        ? String.format(getAndroidAccountsToShareWithText(), name)
        : String.format(getIosAccountsToShareWithText(), name);
    getSoftAssert().assertTrue(isTheElementVisible(By.xpath(accountFullName), 10),
      "Checking mini account name is displayed");
  }

  /**
   * Validates that the correct payment method is shared with the specified Mini account.
   *
   * @param accountFullName The full name of the Mini account.
   * @param cardLastFour        The last four digits of the card number.
   */
  public void validateCorrectPaymentMethodShared(String cardLastFour, String accountFullName) {
    String sharedCardWithAccount = isAndroid()
      ? String.format(getAndroidCardSharedWithAccount(), cardLastFour, accountFullName)
      : String.format(getIosCardSharedWithAccount(), cardLastFour, accountFullName);
    getSoftAssert().assertTrue(isTheElementVisible(By.xpath(sharedCardWithAccount), 10),
      "Share payment method with account is displayed");
  }
}
