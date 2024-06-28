package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.quickIsDisplayed;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.fanapp.mobile.enums.ReminderNames;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.AddPaymentMethodEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.AgeVerificationEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.GameFaceIdEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.MyIdentityPassEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.TeammateAccountsEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.sharepayments.SharePaymentsScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.ageverification.AgeVerificationEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.GameFaceIdEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
import com.automation.lac.qa.utils.CustomException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EducationalQuestions {

  private final AddPaymentMethodEducationalScreen paymentsEduScreen =
    new AddPaymentMethodEducationalScreen();
  private final GameFaceIdEducationalScreen gameFaceEduScreen = new GameFaceIdEducationalScreen();
  private final TeammateAccountsEducationalScreen miniAccountsEduScreen =
    new TeammateAccountsEducationalScreen();
  private final MyIdentityPassEducationalScreen identityPassEduScreen =
    new MyIdentityPassEducationalScreen();
  private final AgeVerificationEducationalScreen ageVerificationEduScreen =
    new AgeVerificationEducationalScreen();
  private final GameFaceIdEducationalTask gameFaceIdEducationalTask =
    new GameFaceIdEducationalTask();
  private final MyIdentityPassEducationalTask myIdentityPassEducationalTask =
    new MyIdentityPassEducationalTask();
  private final AgeVerificationEducationalTask ageVerificationEducationalTask =
    new AgeVerificationEducationalTask();
  private final SharePaymentsScreen sharePaymentsScreen =
    new SharePaymentsScreen();

  String txtAttribute = isAndroid() ? "text" : "label";

  /**
   * Verify elements displayed on add payment educational screen
   */
  public void checkAddPaymentsEducationalScreenElements() {
    getSoftAssert().assertTrue(isTheElementVisible(
        paymentsEduScreen.getLblAddPaymentMethodTitle(), 10),
      "Checking add payment method title is displayed");

    getSoftAssert().assertTrue(isTheElementVisible(
        paymentsEduScreen.getBtnAddPaymentMethod(), 10),
      "Checking add payment method button is displayed");
  }

  /**
   * Verify elements displayed on add identity pass educational screen
   */
  public void checkIdentityPassEducationalScreenElements() {
    getSoftAssert().assertTrue(isTheElementVisible(
        identityPassEduScreen.getLblAddIdentityPassTitle(), 10),
      "Checking add identity pass title is displayed");

    getSoftAssert().assertTrue(isTheElementVisible(
        identityPassEduScreen.getBtnAddIdentityPass(), 10),
      "Checking add identity pass button is displayed");
  }

  /**
   * Verify elements displayed on game face id educational screen
   */
  public void checkGameFaceIdEducationalScreenElements() {
    getSoftAssert().assertTrue(isTheElementVisible(
        gameFaceEduScreen.getLblSetGameFaceIdTitle(), 10),
      "Checking add game face id title is displayed");

    getSoftAssert().assertTrue(isTheElementVisible(
        gameFaceEduScreen.getBtnSetGameFaceId(), 10),
      "Checking add game face id button is displayed");
  }

  /**
   * Verify elements displayed on age verification educational screen
   */
  public void checkAgeVerificationEducationalScreenElements() {
    getSoftAssert().assertTrue(isTheElementVisible(
        ageVerificationEduScreen.getLblVerifyYourAgeTitle(), 10),
      "Checking age verification title is displayed");

    getSoftAssert().assertTrue(isTheElementVisible(
        ageVerificationEduScreen.getBtnVerifyMyAge(), 10),
      "Checking verify my age button is displayed");

    //TODO: Remove task when Age Verification can be completed from reminder
    ageVerificationEducationalTask.clickBackOnAgeVerificationEducationalScreen();
  }

  /**
   * Verify elements displayed on add mini accounts educational screen
   */
  public void checkAddMiniAccountsEducationalScreenElements() {
    //TODO Remove conditional when bug is fixed
    if (!isTheElementVisible(miniAccountsEduScreen.getBtnAddTeammate(), 5))
      throw new CustomException("App crashes when the fan tries to enter to the teammate option."
        + "\nAndroid bug: CA-54852");

    getSoftAssert().assertTrue(isTheElementVisible(
        miniAccountsEduScreen.getImgTeammateAccountsLogo(), 10),
      "Checking mini accounts logo is displayed");

    getSoftAssert().assertTrue(isTheElementVisible(
        miniAccountsEduScreen.getLblTeammateAccountsTitle(), 10),
      "Checking mini accounts title is displayed");

    getSoftAssert().assertTrue(isTheElementVisible(
        miniAccountsEduScreen.getBtnAddTeammate(), 10),
      "Checking add mini accounts button is displayed");
  }

  /**
   * Verify elements displayed on the share mini accounts educational screen.
   */
  public void checkShareMiniAccountsEducationalScreenElements() {
    getSoftAssert().assertEquals(
      sharePaymentsScreen.getLblShareCardsHeaderTitle().getAttribute(txtAttribute),
      "Share with Teammate Account",
      "Share card Header Title is correctly displayed");
    getSoftAssert().assertTrue(isTheElementVisible(
        sharePaymentsScreen.getImgShareWithAccountsLogo(), 10),
      "Checking share card with mini accounts logo is displayed");
    getSoftAssert().assertTrue(
      quickIsDisplayed(sharePaymentsScreen.getLblShareCardsAccountsTitle()),
      "Share card Title is Displayed");
    getSoftAssert().assertTrue(isTheElementVisible(
        sharePaymentsScreen.getBtnSharePaymentMethod(), 10),
      "Checking share payment method with mini accounts button is displayed");
  }


  /**
   * Verify elements displayed on the share fan accounts educational screen.
   */
  public void checkShareFanAccountsEducationalScreenElements() {
    getSoftAssert().assertEquals(
      sharePaymentsScreen.getLblShareCardsHeaderTitle().getText(),
      "Share with a Fan",
      "Share card Header Title is correctly displayed");
    getSoftAssert().assertTrue(isTheElementVisible(
        sharePaymentsScreen.getImgShareWithAccountsLogo(), 10),
      "Checking share card with fan accounts logo is displayed");
    getSoftAssert().assertTrue(
      quickIsDisplayed(sharePaymentsScreen.getLblShareCardsAccountsTitle()),
      "Share card Title is Displayed");
    getSoftAssert().assertTrue(isTheElementVisible(
        sharePaymentsScreen.getBtnSharePaymentMethod(), 10),
      "Checking share payment method with fan accounts button is displayed");
  }

  /**
   * Method that validates one educational screen
   *
   * @param reminderName String to identify reminder related to educational screen
   */
  public void validateEducationalScreen(String reminderName) {
    ReminderNames reminderEnum = ReminderNames.getReminderCardEnum(reminderName);
    switch (reminderEnum) {
      case GAME_FACE_ID -> checkGameFaceIdEducationalScreenElements();
      case ADD_CARDS -> checkAddPaymentsEducationalScreenElements();
      case AGE_VERIFICATION -> checkAgeVerificationEducationalScreenElements();
      case IDENTITY_PASS -> checkIdentityPassEducationalScreenElements();
      case MINI_ACCOUNTS -> checkAddMiniAccountsEducationalScreenElements();
      case SHARE_MINI_ACCOUNT -> checkShareMiniAccountsEducationalScreenElements();
      case SHARE_FAN_ACCOUNT -> checkShareFanAccountsEducationalScreenElements();
      default -> log.warn("Invalid Expected Educational Screen");
    }
  }

  /**
   * Validates if the GameFaceEducationalScreen is Visible
   *
   * @return boolean object
   */
  public boolean isGameFaceEducationalScreenVisible() {
    return isTheElementVisible(gameFaceEduScreen.getBtnSetGameFaceId(), 30);
  }
}