package com.automation.lac.qa.fanapp.mobile.tasks.sharepayments;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SHARE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SHARE_YOUR_PAYMENT_METHODS;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.MINI_ACCOUNTS_INFO;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.SHARED_MINI_ACCOUNT_NAME;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAttributeValue;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.faker.models.userinfo.TeammateInfo;
import com.automation.lac.qa.fanapp.mobile.questions.SharePaymentMethodQuestions;
import com.automation.lac.qa.fanapp.mobile.screens.sharepayments.SharePaymentsScreen;
import java.security.SecureRandom;
import java.util.List;
import org.openqa.selenium.By;

public class SharePaymentMethodsWithAccountsTask extends SharePaymentsScreen {
  private final SharePaymentMethodQuestions sharePaymentMethodQuestions =
    new SharePaymentMethodQuestions();

  private SecureRandom rand = new SecureRandom();

  public void clickOnSharePaymentMethodLink() {
    click(getBtnSharePaymentMethod(), SHARE_YOUR_PAYMENT_METHODS.getValue());
  }

  /**
   * Clicks on the sharing card based on the specified account type.
   *
   * @param accountType the type of account to share with ("mini" for teammate account,
   *                    any other value for fan account)
   */
  public void clickOnSharingCardWithAccount(String accountType) {
    if (accountType.equalsIgnoreCase("mini")) {
      waitForElementToBeClickable(getShareTeammateAccountCard(), 5);
      click(getShareTeammateAccountCard(), "Teammate account share card");
    } else {
      waitForElementToBeClickable(getShareFanAccountCard(), 5);
      click(getShareFanAccountCard(), "Fan account share card");
    }
  }

  /**
   * Shares a payment method with the specified account type.
   * For "mini" accounts, it validates and selects a mini account to share the payment method with.
   * For other account types, it proceeds with the fan account sharing process.
   *
   * @param accountType type of account to share with "mini" or "fan" account)
   */
  public void sharePaymentMethodWithAccountType(String accountType) {
    if (accountType.equalsIgnoreCase("mini")) {
      List<TeammateInfo> miniAccountsList = getTestContext().get(MINI_ACCOUNTS_INFO.name());
      waitForElementVisibility(getDropDownTeammateAccount(), 5);
      click(getDropDownTeammateAccount(), "select one person");
      for (TeammateInfo miniAccountInfo : miniAccountsList) {
        String firstName = miniAccountInfo.getFirstName();
        String lastName = miniAccountInfo.getLastName();
        String expectedName = String.format("%s %s", firstName, lastName);
        sharePaymentMethodQuestions.validateMiniAccountsToShareWith(expectedName);
      }
      int randomIndex = rand.nextInt(miniAccountsList.size());
      TeammateInfo randomOption = miniAccountsList.get(randomIndex);
      String expectedMiniName =
        String.format("%s %s", randomOption.getFirstName(), randomOption.getLastName());
      getTestContext().set(SHARED_MINI_ACCOUNT_NAME.name(), expectedMiniName);
      String miniAccountNameElement =
        isAndroid() ? String.format(getAndroidAccountsToShareWithText(), expectedMiniName) :
          String.format(getIosAccountsToShareWithText(), expectedMiniName);
      click(getElement(By.xpath(miniAccountNameElement)), "Select one person");
      waitForElementAttributeValue(getBtnShare(), "enabled", "true", 3);
      click(getBtnShare(), SHARE.getValue());
    } else {
      // TODO - for share with fan account we need to get the valid email address of fan account.
      waitForElementAttributeValue(getBtnContinue(), "enabled", "true", 3);
      click(getBtnContinue(), CONTINUE.getValue());
      waitForElementToBeClickable(getBtnConfirmAndShare(), 5);
    }
  }
}
