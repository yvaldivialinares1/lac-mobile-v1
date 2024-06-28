package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.CARD_LAST_FOUR_DIGITS;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.SHARED_MINI_ACCOUNT_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.ReminderNames.SHARE_FAN_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ReminderNames.SHARE_MINI_ACCOUNT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.questions.EducationalQuestions;
import com.automation.lac.qa.fanapp.mobile.questions.SharePaymentMethodQuestions;
import com.automation.lac.qa.fanapp.mobile.screens.sharepayments.SharePaymentsScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.sharepayments.SharePaymentMethodsWithAccountsTask;
import com.automation.lac.qa.utils.CustomException;
import io.cucumber.java.en.And;

public class SharePaymentsStepsDefinition extends SharePaymentsScreen {

  private final EducationalQuestions educationalQuestions = new EducationalQuestions();

  private final SharePaymentMethodsWithAccountsTask sharePaymentMethodsWithAccountsTask =
    new SharePaymentMethodsWithAccountsTask();


  private final SharePaymentMethodQuestions sharePaymentMethodQuestions =
    new SharePaymentMethodQuestions();

  /**
   * Shares a payment method with either a Mini or Fan account.
   *
   * @param accountType account to share the payment method with, either "Mini" or "Fan".
   */
  @And("^the user shares a payment method with the (Mini|Fan) account$")
  public void theUserSharePaymentMethodWithAccount(String accountType) {
    sharePaymentMethodsWithAccountsTask.sharePaymentMethodWithAccountType(accountType);
  }

  /**
   * Clicks on the share card option for either a Mini or Fan account.
   *
   * @param accountType The account type to share the card with, either "Mini" or "Fan".
   */
  @And("^the user clicks on share card with (Mini|Fan) account$")
  public void theUserClicksOnCardSharingWithAccount(String accountType) {
    sharePaymentMethodsWithAccountsTask.clickOnSharingCardWithAccount(accountType);
  }

  /**
   * Validates that the correct payment method (preferred or non-preferred) is shared.
   *
   * @param cardType The type of card to validate, either "preferred" or "non preferred".
   */
  @And("^the user validates that the correct payment method is shared with the (Mini|Fan) account$")
  public void validateCorrectPaymentMethodShared(String cardType) {
    String cardLastFour = getTestContext().get(CARD_LAST_FOUR_DIGITS.name());
    if (cardType.equalsIgnoreCase("Mini")) {
      sharePaymentMethodQuestions.validateCorrectPaymentMethodShared(cardLastFour,
        getTestContext().get(SHARED_MINI_ACCOUNT_NAME.name()));
    } else {
      throw new CustomException("Scenario for non-preferred is not available;"
        + " will implement once provided.");
    }
  }

  /**
   * Validates that the user is able to see the educational screen for the shared Mini or Fan card.
   *
   * @param accountType The type of account, either "Mini" or "Fan".
   */
  @And("^the user is able to see the educational screen for (Mini|Fan) shared card$")
  public void validateEducationalScreenForSharedCard(String accountType) {
    String account = accountType.equalsIgnoreCase("mini") ? SHARE_MINI_ACCOUNT.getName() :
      SHARE_FAN_ACCOUNT.getName();
    educationalQuestions.validateEducationalScreen(account);
    sharePaymentMethodsWithAccountsTask.clickOnSharePaymentMethodLink();
  }
}
