package com.automation.lac.qa.fanapp.mobile.utils;

import static com.automation.lac.qa.faker.enums.FanType.ADULT;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.tasks.ageverification.AgeVerificationEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.GameFaceIdEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.VerifyYourAgeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.home.HomeTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentsmethod.AddPaymentMethodEducationalTask;
import com.automation.lac.qa.utils.CustomException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NavigationUtils {

  /**
   * Navigates from one section of the application to another based on the specified
   * `from` and `to` parameters. This method uses a switch statement to handle different
   * navigation paths and performs the necessary actions to navigate between sections.
   * If an unsupported navigation path is provided, a {@link CustomException} is thrown.
   *
   * @param from the starting section of the navigation
   * @param to   the destination section of the navigation
   * @throws CustomException if the specified navigation path is not supported
   */
  public static void navigate(String from, String to) {
    String key = from + " to " + to;
    switch (key) {
      case "Home to My Profile":
        new HomeTasks().goToMyProfileFromHome();
        break;

      case "Home to My Account Settings":
        new HomeTasks().goToMyProfileFromHome().goToMyAccountSettings();
        break;

      case "My Profile to My Account Settings":
        new MyLoggedProfileTask().goToMyAccountSettings();
        break;

      case "My Account Settings to My Payments":
        new MyLoggedProfileTask().goToMyPayments();
        break;

      default:
        throw new CustomException("Navigation not supported: " + key);
    }
  }

  /**
   * Manages the transitions between different onboarding stages based on the provided steps.
   * Depending on the provided steps and the platform (Android or iOS), this method will execute
   * corresponding tasks for the onboarding process, such as completing or skipping the Game Face
   * ID, verifying age, adding payment methods, and completing or skipping the identity pass.
   *
   * @param steps A list of steps to be included in the onboarding process.
   */
  public void onboardingStagesTransition(List<String> steps) {

    Runnable completeGameFaceID = () -> new GameFaceIdEducationalTask().setMyGameFaceId();
    Runnable skipGameFaceID = () -> new GameFaceIdEducationalTask().skipGameFaceEducationalScreen();
    Runnable continueAgeVerification = () -> new VerifyYourAgeTask().clickOnContinue();
    Runnable skipAgeVerification =
      () -> new AgeVerificationEducationalTask().skipAgeVerificationRegistration();

    Runnable addPaymentMethods =
      () -> new AddPaymentMethodEducationalTask().completePaymentMethodRegistration();
    Runnable skipPaymentMethod =
      () -> new AddPaymentMethodEducationalTask().skipPaymentMethodsRegistration();

    Runnable completeIdentityPass =
      () -> new MyIdentityPassEducationalTask().clickOnAddIdentityPass();
    Runnable skipIdentityPass =
      () -> new MyIdentityPassEducationalTask().skipMyIdentityPassEducationalScreen();


    List<Runnable> actions = new ArrayList<>();

    // ToDo: Remove the SO validation once the mock is available for iOS
    if (steps.isEmpty() || !isAndroid()) {
      actions.add(skipGameFaceID);
      actions.add(skipIdentityPass);
      actions.add(skipPaymentMethod);
    } else {
      if (steps.contains("Game Face ID")) {
        actions.add(steps.contains("Game Face ID") ? completeGameFaceID : skipGameFaceID);
        actions.add(
          !getTestContext().get("userType").toString().equalsIgnoreCase(ADULT.name())
            ? continueAgeVerification : skipAgeVerification);

        actions.add(steps.contains("Payment Methods") ? addPaymentMethods : skipPaymentMethod);
        actions.add(steps.contains("Identity Pass") ? completeIdentityPass : skipIdentityPass);
      } else {
        actions.add(skipGameFaceID);
        actions.add(steps.contains("Identity Pass") ? completeIdentityPass : skipIdentityPass);
        actions.add(steps.contains("Payment Methods") ? addPaymentMethods : skipPaymentMethod);
      }
    }
    actions.forEach(Runnable::run);
  }

}