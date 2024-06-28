package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.BannerMessages.GAME_FACE_AND_AGE_SUCCESSFULLY_ADDED;
import static com.automation.lac.qa.fanapp.mobile.enums.ReminderNames.AGE_VERIFICATION;

import com.automation.lac.qa.fanapp.mobile.questions.HomeQuestions;
import com.automation.lac.qa.fanapp.mobile.questions.MyLoggedProfileQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyAccountSettingsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import io.cucumber.java.en.And;

public class MyLoggedProfileStepsDefinition {

  private final MyLoggedProfileTask myProfileLoggedTasks = new MyLoggedProfileTask();
  private final MyLoggedProfileQuestions myLoggedProfileQuestions = new MyLoggedProfileQuestions();
  private final MyAccountSettingsTasks myAccountSettingsTasks = new MyAccountSettingsTasks();
  private final MyLoggedProfileTask myLoggedProfileTasks = new MyLoggedProfileTask();

  private final HomeQuestions homeQuestions = new HomeQuestions();

  /**
   * Valid string values:
   * "Game Face ID"
   * "Age Verification"
   * "Payment Method"
   * "Identity Pass"
   */
  @And("^the user taps on the "
    + "(Game Face ID|Age Verification|Payment Method|Identity Pass) reminder$")
  public void theUserTapsOnReminderCard(String reminderName) {
    myProfileLoggedTasks.goToReminderCard(reminderName);
    myProfileLoggedTasks.clickOnReminder(reminderName);
  }

  @And("the user closes the section in the app")
  public void theUserClosesTheSectionInTheApp() {
    myProfileLoggedTasks.logoutFromTheApp();
    homeQuestions.checkElementsOnScreen();
  }

  @And("the user is able to see the ticket that was purchased before")
  public void theUserSeesTheTicketInTheProfile() {
    myLoggedProfileQuestions.checkPurchasedTicket();
  }

  /**
   * Valid string values:
   * "Game Face ID"
   * "Age Verification"
   * "Payment Method"
   * "Identity Pass"
   */
  @And("^the (.*) reminder is( not)? displayed$")
  public void theUserValidatesReminderIsNotVisible(String reminderName, String condition) {
    myLoggedProfileQuestions.validateReminderVisibility(reminderName, condition);
  }

  /**
   * Executes the interaction where the user taps on the age verification reminder.
   * The method navigates to the age verification reminder card, clicks on the reminder
   */
  @And("the user taps on the age verification reminder")
  public void theUserTapsOnTheAgeVerificationReminder() {
    myLoggedProfileTasks.goToReminderCard(AGE_VERIFICATION.getName());
    myLoggedProfileTasks.clickOnReminder(AGE_VERIFICATION.getName());
  }

  @And("the user navigates to My Vehicles from My Profile")
  public void theUserNavigatesToMyVehiclesFromProfile() {
    myLoggedProfileTasks.goToMyVehicles();
  }

  /**
   * Users clicks On My payments In My Profile
   */
  @And("the user navigates to my payments from my profile")
  public void theUserNavigatesToMyPaymentsFromProfile() {
    myLoggedProfileTasks.goToMyPayments();
  }

  /**
   * Users get back to home from My Profile
   */
  @And("the user navigates from profile to home")
  public void userNavigatesFromProfileToHome() {
    myLoggedProfileTasks.clickBackOnMyProfile();
  }

  @And("the user returns to the my account settings screen from My Payment Methods Management")
  public void theUserReturnsToTheMyAccountSettingsScreenFromMyPaymentMethodsManagement() {
    myAccountSettingsTasks.goToMyAccountSettingsFromMyPaymentMethodsManagement();
  }

  @And("the user returns to the Profile screen from My Payment Methods Management")
  public void theUserReturnsToTheProfileScreenFromMyPaymentMethodsManagement() {
    myAccountSettingsTasks.goToMyProfileFromMyPaymentMethodsManagement();
  }

  @And("the app shows success message for game face id registration")
  public void theAppShowsSuccessMessageForGameFaceIdRegistration() {
    myLoggedProfileQuestions.validateBannerMessage(
      GAME_FACE_AND_AGE_SUCCESSFULLY_ADDED.getValue());
  }
}