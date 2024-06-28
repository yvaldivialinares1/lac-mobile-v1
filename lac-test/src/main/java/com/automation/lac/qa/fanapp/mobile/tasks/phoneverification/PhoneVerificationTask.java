package com.automation.lac.qa.fanapp.mobile.tasks.phoneverification;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.ACCOUNT_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.LAST_OTP_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PHONE_VERIFICATION_RESULT;
import static com.automation.lac.qa.onlinesim.OnlineSimApi.getOtpMessages;
import static com.automation.lac.qa.onlinesim.OnlineSimApi.mostRecentDateTime;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.performText;
import static com.automation.lac.qa.utils.mobile.WaitActions.createFluentWait;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;
import static io.qameta.allure.model.Status.PASSED;
import static io.qameta.allure.model.Status.SKIPPED;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.faker.models.userinfo.PhoneInfo;
import com.automation.lac.qa.fanapp.mobile.screens.phonenumbervalidation.PhoneNumberValidationScreen;
import com.automation.lac.qa.onlinesim.models.OnlineSimResponse.Message;
import com.automation.lac.qa.pages.MobileBaseScreen;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

public class PhoneVerificationTask extends PhoneNumberValidationScreen {

  /**
   * Retrieves the Phone OTP code input process by completing the OTP code field
   * with the provided OTP code and clicking the continue button.
   * It's necessary to validate if the OTP screen is visible due to a random issue CA-45223
   *
   * @param userInfo The user info from the user.
   */
  public void retrieveManagementPhoneOtp(UserInfo userInfo) {
    boolean isPhoneOtpScreenVisible = waitPhoneOtpScreen();

    if (isPhoneOtpScreenVisible) {
      getTestContext().set(ACCOUNT_CREATION_RESULT.name(), PASSED);

      PersonalInfo personalInfo = userInfo.getPersonalInfo();
      PhoneInfo phoneInfo = userInfo.getPhoneInfo();
      FluentWait<Object> wait =
        createFluentWait(MobileBaseScreen.getDriver(), Duration.ofMinutes(2),
          Duration.ofSeconds(10),
          "Was not possible to set OTP number to complete the register",
          NoSuchElementException.class);

      wait.until(result -> {
        List<Message> otpMessages = getOtpMessages(phoneInfo.getPhoneOtpCountryCode(),
          personalInfo.getPhoneNumber(), getTestContext().get(LAST_OTP_DATE.name()));
        boolean otpSuccessful = false;
        for (Message message : otpMessages) {
          managePhoneOtpCode(extractNumber(message.getCode()));
          if (!isTheElementVisible(getMsgWrongOtpCode(), 5)) {
            otpSuccessful = true;
            break;
          }
        }
        if (!otpSuccessful && !otpMessages.isEmpty()) {
          LocalDateTime mostRecentDateTime = mostRecentDateTime(otpMessages);
          getTestContext().set(LAST_OTP_DATE.name(), mostRecentDateTime);
        }
        return otpSuccessful;
      });
    } else {
      getTestContext().set(PHONE_VERIFICATION_RESULT.name(), SKIPPED);
    }
  }

  /**
   * Manages the Phone OTP code input process by completing the OTP code field
   * with the provided OTP code and clicking the continue button.
   *
   * @param phoneVerificationCode The OTP code to be entered.
   */
  public void managePhoneOtpCode(String phoneVerificationCode) {
    completePhoneVerificationCode(phoneVerificationCode);
    click(getBtnConfirmPhoneNumber(), CONFIRM.getValue());
  }

  /**
   * Completes the Phone OTP code input process
   * clicking on the OTP code input field, and entering the provided OTP code.
   *
   * @param phoneVerificationCode The OTP code to be entered.
   */
  public void completePhoneVerificationCode(String phoneVerificationCode) {
    waitForElementToBeClickable(getTxtPhoneVerificationCode(), 5);
    performText(getTxtPhoneVerificationCode(), phoneVerificationCode, "OTP");
  }

  /**
   * Waits for the phone OTP (One-Time Password) screen to be displayed.
   * The method should include a proper timeout and conditions to detect the OTP screen presence.
   */
  public boolean waitPhoneOtpScreen() {
    boolean isPhoneOtpScreenVisible = isPhoneNumberValidationScreenDisplayed(20);
    if (isPhoneOtpScreenVisible) {
      waitForElementToBeClickable(getLblOtpNotification(), 10);
    }

    return isPhoneOtpScreenVisible;
  }

  private static String extractNumber(String input) {
    return input.replaceAll("\\D+", "");
  }

  public boolean isPhoneNumberValidationScreenDisplayed(int timeout) {
    return isTheElementVisible(getTxtPhoneVerificationCode(), timeout);
  }
}