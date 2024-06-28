package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.mobile.enums.LoginBannerMessage.EMAIL_EXIST_FOR_NBA;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementAvailable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;

import com.automation.lac.qa.fanapp.mobile.screens.login.LoginScreen;

public class LoginScreenQuestions extends LoginScreen {

  /**
   * Question to check elements on screen
   * option 1 to assert all
   * SoftAssertManager.assertAll();
   * option 2 wait at the end of the test,assertAll() will be executed in the hook
   */
  public void checkElementsOnScreen() {
    if (!isAndroid())
      waitElementWillBeAvailable(getLblWelcomeHome(), 4);
    getSoftAssert()
      .assertTrue(getInputEmailAddress().isDisplayed(), "Checking email field displayed");
    getSoftAssert()
      .assertTrue(getInputPassword().isDisplayed(), "Checking password field is displayed");
    getSoftAssert()
      .assertTrue(getBtnSignIn().isDisplayed(), "Checking sigIn button was displayed");
  }

  /**
   * Validate if the banner 'EmailExistForNba' message is displayed on Login screen
   */
  public void isBannerEmailExistForNbaDisplayed() {
    getSoftAssert().assertTrue(isTheElementAvailable(getBannerEmailExistForNba(), 2));
  }

  /**
   * Validate a message in the banner EmailExistForNba
   */
  public void validateBannerEmailExistForNbaMessage() {
    if (isAndroid()) {
      getSoftAssert().assertEquals(getBannerEmailExistForNba().getAttribute("content-desc"),
        EMAIL_EXIST_FOR_NBA.getValue(),
        String.format("The banner has the text '%s'", EMAIL_EXIST_FOR_NBA.getValue()));
    } else {
      getSoftAssert().assertEquals(getBannerEmailExistForNba().getText(),
        EMAIL_EXIST_FOR_NBA.getValue(),
        String.format("The banner has the text '%s'", EMAIL_EXIST_FOR_NBA.getValue()));
    }
  }
}