package com.automation.lac.qa.usercreation.tasks;

import static com.automation.lac.qa.allure.AllureLogger.attachScreenShot;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.ACCOUNT_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.CURRENT_USER_TO_CREATE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.GAME_FACE_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IDENTITY_PASS_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_CARD_ADDED;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.LAST_USER_INFO_ON_CONTEXT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.MOCK_ACTIVATION_STATUS;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PAYMENT_METHODS_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PHONE_VERIFICATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PREFERRED_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.TEAM_MATE_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.TOTAL_USERS_TO_CREATE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.VEHICLES_CREATION_RESULT;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.usercreation.utils.UserCreationBuilder.removeCreationResult;
import static com.automation.lac.qa.usercreation.utils.UserCreationBuilder.userCreationResultBuilder;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.UserCreationConstant.FANS_NUMBER;
import static com.automation.lac.qa.utils.UserCreationConstant.GAME_FACE_ID;
import static com.automation.lac.qa.utils.UserCreationConstant.IDENTITY_PASS;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.model.Status.FAILED;
import static io.qameta.allure.model.Status.PASSED;
import static java.lang.String.format;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.UserInfoByFanType;
import com.automation.lac.qa.fanapp.mobile.tasks.developersettings.DeveloperSettingsTask;
import com.automation.lac.qa.fanapp.mobile.tasks.modals.GoCashlessModalTask;
import com.automation.lac.qa.usercreation.models.TeamMateCreationResult;
import com.automation.lac.qa.usercreation.models.TicketPurchaseResult;
import com.automation.lac.qa.usercreation.models.UserCreationResult;
import com.automation.lac.qa.usercreation.models.VehicleCreationResult;
import io.qameta.allure.model.Status;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCreationTask {

  private final GameFaceIdentityAndPaymentTask gameFaceIdentityAndPaymentTask =
    new GameFaceIdentityAndPaymentTask();
  private final AccountCreationTask accountCreationTask = new AccountCreationTask();
  private final TeammateVehicleAndTicketTask miniAccountVehicleAndTicketTask =
    new TeammateVehicleAndTicketTask();
  private final GoCashlessModalTask goCashlessModalTask = new GoCashlessModalTask();
  private final DeveloperSettingsTask developerSettingsTask = new DeveloperSettingsTask();

  /**
   * Create a list of users, completing all the available  flows to create a user
   *
   * @param usersInfo the user info list with the same Fan Type
   * @return UserCreationResult list
   */
  public List<UserCreationResult> createUserListByTheFanType(List<UserInfo> usersInfo) {
    List<UserCreationResult> usersCreationResultList = new ArrayList<>();
    Status userCreationStatus;

    for (UserInfo userInfo : usersInfo) {

      showTheUserCreationStatusOnTheLog();

      try {
        step(format("***************User -> %s", userInfo.getAccountInfo().getEmail()));
        getTestContext().set(USER_INFO.name(), userInfo);

        completeTheUserCreationFlow(userInfo);

        userCreationStatus = PASSED;

      } catch (Exception e) {
        userCreationStatus = FAILED;
        step(e.getMessage(), userCreationStatus);
        attachScreenShot("userCreationError");
      }

      usersCreationResultList.add(userCreationResultBuilder(userInfo.getAccountInfo().getEmail(),
        userInfo.getAccountInfo().getPassword(), userCreationStatus));

      if (!(boolean) getTestContext().get(LAST_USER_INFO_ON_CONTEXT.name()).equals(userInfo)) {
        accountCreationTask.restartTheAppAndLogout();
        removeUnnecessaryLastResult();
      }
    }

    return usersCreationResultList;
  }

  /**
   * Integrate all the user creation flows
   */
  public void completeTheUserCreationFlow(UserInfo userInfo) {
    accountCreationTask.createAccountAndCompletePhoneValidation(userInfo);
    gameFaceIdentityAndPaymentTask.manageGameFaceId();
    gameFaceIdentityAndPaymentTask.manageIdentityPass();
    gameFaceIdentityAndPaymentTask.managePaymentMethod().clickTakeMeToIntuitDome();
    if (!(boolean) getTestContext().get(IS_CARD_ADDED.name()))
      goCashlessModalTask.clickOnRemindMeLater();
    miniAccountVehicleAndTicketTask.goToMyProfile();
    miniAccountVehicleAndTicketTask.manageTeammate();
    miniAccountVehicleAndTicketTask.manageVehicle();
    miniAccountVehicleAndTicketTask.manageTicket();
  }

  /**
   * Shows the user creation result on the Allure report
   */
  public void showsTheUserCreationResult(List<UserCreationResult> userCreationResultList) {
    for (UserCreationResult userCreationResult : userCreationResultList) {
      step(format("*User %s -> *Email: %s *Password: %s *Result: %s",
          userCreationResultList.indexOf(userCreationResult) + 1, userCreationResult.getEmail(),
          userCreationResult.getPassword(), userCreationResult.getStatus().name()),
        userCreationResult.getStatus());
      showAccountCreationAndPhoneOnTheReport(userCreationResult);
      showGameFaceIdentityAndPaymentOnTheReport(userCreationResult);
      showTeammateVehicleAndTicketOnTheReport(userCreationResult.getTeamMateCreationResult(),
        userCreationResult.getVehicleCreationResult(),
        userCreationResult.getTicketPurchaseResult());
    }
  }

  /**
   * Shows Account Creation and Phone Verification flows result on the Allure report
   *
   * @param userCreationResult object
   */
  public void showAccountCreationAndPhoneOnTheReport(UserCreationResult userCreationResult) {
    if (userCreationResult.getAccountCreationResult() != null)
      step(format("***Account Creation: %s", userCreationResult.getAccountCreationResult()),
        userCreationResult.getAccountCreationResult());
    if (userCreationResult.getPhoneVerificationResult() != null)
      step(
        format("***Phone Verification: %s", userCreationResult.getPhoneVerificationResult()),
        userCreationResult.getPhoneVerificationResult());
  }

  /**
   * Shows GameFace, Identity, and Payment method flows result on the Allure report
   *
   * @param userCreationResult object
   */
  public void showGameFaceIdentityAndPaymentOnTheReport(UserCreationResult userCreationResult) {
    if (userCreationResult.getGameFaceIdResult() != null) {
      Status gameFaceIdResult = userCreationResult.getGameFaceIdResult();
      step(format("***Game Face: %s", gameFaceIdResult.equals(PASSED) ? "(Mocked)" :
        userCreationResult.getGameFaceIdResult()), userCreationResult.getGameFaceIdResult());
    }
    if (userCreationResult.getIdentityPassResult() != null) {
      Status identityPassResult = userCreationResult.getIdentityPassResult();
      step(format("***Identity Pass: %s", identityPassResult.equals(PASSED) ? "(Mocked)" :
        identityPassResult), identityPassResult);
    }
    if (userCreationResult.getPaymentMethodResult() != null)
      step(format("***Payment Method: %s", userCreationResult.getPaymentMethodResult()),
        userCreationResult.getPaymentMethodResult());
  }

  /**
   * Shows Teammate and Vehicle flows result on the Allure report
   *
   * @param teamMateCreationResult object
   * @param vehicleCreationResult  object
   * @param ticketPurchaseResult   object
   */
  public void showTeammateVehicleAndTicketOnTheReport(TeamMateCreationResult teamMateCreationResult,
                                                      VehicleCreationResult vehicleCreationResult,
                                                      TicketPurchaseResult ticketPurchaseResult) {
    if (teamMateCreationResult != null)
      step(format("***Teammates: %s", teamMateCreationResult.getResultMessage()),
        teamMateCreationResult.getStatus());
    if (vehicleCreationResult != null)
      step(format("***Vehicles: %s",
        vehicleCreationResult.getResultMessage()), vehicleCreationResult.getStatus());
    if (ticketPurchaseResult != null)
      step(format("***Ticket Purchase result: %s",
        ticketPurchaseResult.getStatus()), ticketPurchaseResult.getStatus());
  }

  /**
   * Sets the last user info on the list and the total and current user to create
   *
   * @param usersInfoByFanType the list of user info
   */
  public void setTheUserInfoValuesOnTheContext(List<UserInfoByFanType> usersInfoByFanType) {
    UserInfoByFanType lastUserInfoByFanType = usersInfoByFanType.get(usersInfoByFanType.size() - 1);

    getTestContext().set(LAST_USER_INFO_ON_CONTEXT.name(), lastUserInfoByFanType.getUsersInfo()
      .get(lastUserInfoByFanType.getUsersInfo().size() - 1));

    getTestContext().set(TOTAL_USERS_TO_CREATE.name(),
      (usersInfoByFanType.stream().count() * (FANS_NUMBER > 10 ? 10 : FANS_NUMBER)));
    getTestContext().set(CURRENT_USER_TO_CREATE.name(), 0);
  }

  /**
   * Removes the last creation results from the TestContext
   */
  public void removeUnnecessaryLastResult() {
    removeCreationResult(ACCOUNT_CREATION_RESULT.name());
    removeCreationResult(PHONE_VERIFICATION_RESULT.name());
    removeCreationResult(GAME_FACE_CREATION_RESULT.name());
    removeCreationResult(IDENTITY_PASS_CREATION_RESULT.name());
    removeCreationResult(PAYMENT_METHODS_CREATION_RESULT.name());
    removeCreationResult(TEAM_MATE_CREATION_RESULT.name());
    removeCreationResult(VEHICLES_CREATION_RESULT.name());
    removeCreationResult(PREFERRED_CARD.name());
  }

  /**
   * Shows which user is being created to know the progress of the process
   */
  public void showTheUserCreationStatusOnTheLog() {
    int currentUserToCreate = ((int) getTestContext().get(CURRENT_USER_TO_CREATE.name())) + 1;
    log.info(format("Creating user %s of %s", currentUserToCreate,
      getTestContext().get(TOTAL_USERS_TO_CREATE.name())));
    getTestContext().set(CURRENT_USER_TO_CREATE.name(), currentUserToCreate);
  }

  /**
   * Allows to activate the mock option, according to the given parameters
   *
   * @param userType the FanType parameter for setting the mock
   */
  public void activateMocksDependingTheOptions(String userType) {
    if (isAndroid() && GAME_FACE_ID || IDENTITY_PASS) {
      try {
        developerSettingsTask.setMocksWith()
          .setFocus()
          .gameFaceIdForUserType(userType)
          .setIdentityPassBypass(true)
          .closeScreen();
      } catch (Exception e) {
        step(
          "An error has occurred during the mock activation option, so the flows will be skipped");
        attachScreenShot("mockActivationError");
        getTestContext().set(MOCK_ACTIVATION_STATUS.name(), false);
      }
    }
  }
}