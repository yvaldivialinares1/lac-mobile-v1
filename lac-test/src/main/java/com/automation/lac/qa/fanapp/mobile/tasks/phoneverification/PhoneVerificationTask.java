package com.automation.lac.qa.fanapp.mobile.tasks.phoneverification;

import static com.automation.lac.qa.driver.AppiumConstants.NEW_COMMAND_TIMEOUT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.LAST_OTP_DATE;
import static com.automation.lac.qa.onlinesim.OnlineSimApi.getOtpMessages;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.performText;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementUnavailability;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.faker.models.userinfo.PhoneInfo;
import com.automation.lac.qa.fanapp.mobile.screens.phonenumbervalidation.PhoneNumberValidationScreen;
import com.automation.lac.qa.onlinesim.models.OnlineSimResponse.Message;
import com.automation.lac.qa.utils.CustomException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneVerificationTask extends PhoneNumberValidationScreen {

  /**
   * Retrieves the Phone OTP code input process by completing the OTP code field
   * with the provided OTP code and clicking the continue button.
   *
   * @param userInfo The OTP code to be entered.
   */
  public void retrieveManagementPhoneOtp(UserInfo userInfo) {
    waitPhoneOtpScreen();

    PersonalInfo personalInfo = userInfo.getPersonalInfo();
    PhoneInfo phoneInfo = userInfo.getPhoneInfo();
    getDriver().manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
    List<Message> otpMessages = getOtpMessages(phoneInfo.getPhoneOtpCountryCode(),
      personalInfo.getPhoneNumber(), getTestContext().get(LAST_OTP_DATE.name()));
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    List<Message> otpListRetry;
    boolean otpSuccessful = false;
    for (Message message : otpMessages) {
      managePhoneOtpCode(message.getCode());
      if (!waitForElementVisibility(getMsgWrongOtpCode(), 5)) {
        otpSuccessful = true;
        break;
      }
    }
    if (!otpSuccessful) {
      List<LocalDateTime> dateTimes = new ArrayList<>();
      for (Message message : otpMessages) {
        dateTimes.add(LocalDateTime.parse(message.getCreatedAt().replace(" ", "T")));
      }
      LocalDateTime mostRecentDateTime = Collections.max(dateTimes);
      getDriver().manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
      otpListRetry = getOtpMessages(phoneInfo.getPhoneOtpCountryCode(),
        personalInfo.getPhoneNumber(), mostRecentDateTime);
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
      List<String> oldOtpList =
        new ArrayList<>(otpMessages.stream().map(Message::getCode).toList());
      List<String> newOtpList =
        new ArrayList<>(otpListRetry.stream().map(Message::getCode).toList());
      newOtpList.removeAll(oldOtpList);
      if (newOtpList.isEmpty())
        throw new CustomException("The OTP service doesn't have new SMS");
      for (String otp : newOtpList) {
        managePhoneOtpCode(otp);
        if (!waitForElementVisibility(getMsgWrongOtpCode(), 3)) {
          otpSuccessful = true;
          break;
        }
      }
    }
    if (!otpSuccessful)
      throw new CustomException("Was not possible to get OTP number to complete the register");
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
    waitForElementToBeClickable(5, getTxtPhoneVerificationCode());
    performText(getTxtPhoneVerificationCode(), phoneVerificationCode, "OTP");
    hideKeyboard(null);
  }

  /**
   * Waits for the phone OTP (One-Time Password) screen to be displayed.
   * The method should include a proper timeout and conditions to detect the OTP screen presence.
   */
  public void waitPhoneOtpScreen() {
    waitForElementToBeClickable(20, getTxtPhoneVerificationCode());
    waitForElementUnavailability(getLblOtpNotification(), 20);
  }
}