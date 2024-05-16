package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.mobile.enums.ReminderNames;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.AddPaymentMethodEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.AgeVerificationEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.GameFaceIdEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.MyIdentityPassEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.TeammateAccountsEducationalScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.ageverification.AgeVerificationEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.GameFaceIdEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
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

  /**
   * Verify elements displayed on add payment educational screen
   */
  public void checkAddPaymentsEducationalScreenElements() {
    getSoftAssert().assertTrue(paymentsEduScreen.getImgAddPaymentMethodLogo().isDisplayed(),
      "Checking add payment method logo is displayed");

    getSoftAssert().assertTrue(paymentsEduScreen.getLblAddPaymentMethodTitle().isDisplayed(),
      "Checking add payment method title is displayed");

    getSoftAssert().assertTrue(paymentsEduScreen.getBtnAddPaymentMethod().isDisplayed(),
      "Checking add payment method button is displayed");
  }

  /**
   * Verify elements displayed on add identity pass educational screen
   */
  public void checkIdentityPassEducationalScreenElements() {
    getSoftAssert().assertTrue(identityPassEduScreen.getImgAddIdentityPassLogo().isDisplayed(),
      "Checking add identity pass logo is displayed");

    getSoftAssert().assertTrue(identityPassEduScreen.getLblAddIdentityPassTitle().isDisplayed(),
      "Checking add identity pass title is displayed");

    getSoftAssert().assertTrue(identityPassEduScreen.getBtnAddIdentityPass().isDisplayed(),
      "Checking add identity pass button is displayed");

    //TODO: Remove task when Identity Pass can be completed from reminder
    myIdentityPassEducationalTask.clickOnBackButtonEducationalScreen();
  }

  /**
   * Verify elements displayed on game face id educational screen
   */
  public void checkGameFaceIdEducationalScreenElements() {
    getSoftAssert().assertTrue(gameFaceEduScreen.getImgGameFaceIdLogo().isDisplayed(),
      "Checking add game face id logo is displayed");

    getSoftAssert().assertTrue(gameFaceEduScreen.getLblSetGameFaceIdTitle().isDisplayed(),
      "Checking add game face id title is displayed");

    getSoftAssert().assertTrue(gameFaceEduScreen.getBtnSetGameFaceId().isDisplayed(),
      "Checking add game face id button is displayed");

    //TODO: Remove task when GameFace can be completed from reminder
    gameFaceIdEducationalTask.clickBackOnGameFaceEducationalScreen();
  }

  /**
   * Verify elements displayed on age verification educational screen
   */
  public void checkAgeVerificationEducationalScreenElements() {
    getSoftAssert().assertTrue(ageVerificationEduScreen.getImgAgeVerificationLogo().isDisplayed(),
      "Checking age verification logo is displayed");

    getSoftAssert().assertTrue(ageVerificationEduScreen.getLblVerifyYourAgeTitle().isDisplayed(),
      "Checking age verification title is displayed");

    getSoftAssert().assertTrue(ageVerificationEduScreen.getBtnVerifyMyAge().isDisplayed(),
      "Checking verify my age button is displayed");

    //TODO: Remove task when Age Verification can be completed from reminder
    ageVerificationEducationalTask.clickBackOnAgeVerificationEducationalScreen();
  }

  /**
   * Verify elements displayed on add mini accounts educational screen
   */
  public void checkAddMiniAccountsEducationalScreenElements() {
    getSoftAssert().assertTrue(miniAccountsEduScreen.getImgTeammateAccountsLogo().isDisplayed(),
      "Checking mini accounts logo is displayed");

    getSoftAssert().assertTrue(miniAccountsEduScreen.getLblTeammateAccountsTitle().isDisplayed(),
      "Checking mini accounts title is displayed");

    getSoftAssert().assertTrue(miniAccountsEduScreen.getBtnAddTeammate().isDisplayed(),
      "Checking add mini accounts button is displayed");
  }

  /**
   * Method that validates one educational screen
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
      default -> log.warn("Invalid Expected Educational Screen");
    }
  }
}