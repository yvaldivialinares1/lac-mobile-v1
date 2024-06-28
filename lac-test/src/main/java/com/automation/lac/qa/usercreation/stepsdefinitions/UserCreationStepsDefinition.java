package com.automation.lac.qa.usercreation.stepsdefinitions;

import static com.automation.lac.qa.faker.enums.FanType.ADULT;
import static com.automation.lac.qa.faker.enums.FanType.ALL_ADULT;
import static com.automation.lac.qa.faker.enums.FanType.MINOR_ADULT;
import static com.automation.lac.qa.faker.enums.FanType.YOUNG_ADULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_CARD_ADDED;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.MOCK_ACTIVATION_STATUS;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO_BY_FAN_TYPE;
import static com.automation.lac.qa.usercreation.utils.UserCreationBuilder.userCreationResultByFanBuilder;
import static com.automation.lac.qa.utils.Constants.APP_MOCKS;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.UserCreationConstant.FAN_TYPE;
import static com.automation.lac.qa.utils.UserCreationConstant.GAME_FACE_ID;
import static com.automation.lac.qa.utils.UserCreationConstant.IDENTITY_PASS;
import static com.automation.lac.qa.utils.UserCreationConstant.PAYMENT_METHOD;
import static com.automation.lac.qa.utils.UserCreationConstant.TEAMMATE;
import static com.automation.lac.qa.utils.UserCreationConstant.VEHICLE;
import static io.qameta.allure.model.Status.PASSED;
import static java.lang.String.format;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.faker.models.UserInfoByFanType;
import com.automation.lac.qa.fanapp.mobile.questions.WelcomeHomeQuestion;
import com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions.TurnOnNotificationTask;
import com.automation.lac.qa.usercreation.models.UserCreationResult;
import com.automation.lac.qa.usercreation.models.UserCreationResultByFanType;
import com.automation.lac.qa.usercreation.tasks.UserCreationTask;
import com.automation.lac.qa.usercreation.utils.UserCreationBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import java.util.ArrayList;
import java.util.List;

public class UserCreationStepsDefinition {

  private final UserCreationTask userCreationTask = new UserCreationTask();
  private final TurnOnNotificationTask turnOnNotificationTask = new TurnOnNotificationTask();
  private final WelcomeHomeQuestion welcomeHomeQuestion = new WelcomeHomeQuestion();

  /**
   * Sets all the data for creating the users
   */
  @Given("there are a number and type of users who have personal information generated")
  public void thereAreANumberAndTypeOfUsersWhoHavePersonalInformationGenerated() {
    List<FanType> fanTypeList = FAN_TYPE.equals(ALL_ADULT)
      ? List.of(MINOR_ADULT, YOUNG_ADULT, ADULT) : List.of(FAN_TYPE);

    getTestContext().set(USER_INFO_BY_FAN_TYPE.name(), fanTypeList.stream().map(
      UserCreationBuilder::userInfoByFanTypeBuilder).toList());
    getTestContext().set(IS_CARD_ADDED.name(), false);
    getTestContext().set(MOCK_ACTIVATION_STATUS.name(), APP_MOCKS);
  }

  /**
   * Creates all the users according to the given parameters
   */
  @When("the users completes all the flow according to the given parameters")
  public void theUsersCompletesAllTheFlowAccordingToTheGivenParameters() {
    turnOnNotificationTask.grantLacPermission();
    welcomeHomeQuestion.checkWelcomeHomeScreenIsDisplayed();

    List<UserCreationResultByFanType> userCreationResultByUserTypes = new ArrayList<>();
    List<UserInfoByFanType> userInfoByFanTypes =
      getTestContext().get(USER_INFO_BY_FAN_TYPE.name());

    userCreationTask.setTheUserInfoValuesOnTheContext(userInfoByFanTypes);

    for (UserInfoByFanType userInfoByUserType : userInfoByFanTypes) {
      if (APP_MOCKS && userInfoByUserType.getFanType().equals(ADULT)
        || userInfoByUserType.getFanType().equals(YOUNG_ADULT))
        userCreationTask.activateMocksDependingTheOptions(userInfoByUserType.getFanType().name());

      List<UserCreationResult> usersCreationResultList =
        userCreationTask.createUserListByTheFanType(userInfoByUserType.getUsersInfo());

      userCreationResultByUserTypes.add(
        userCreationResultByFanBuilder(userInfoByUserType.getFanType(),
          usersCreationResultList));
    }

    getTestContext().set(USER_CREATION_RESULT.name(), userCreationResultByUserTypes);
    if (((boolean) getTestContext().get(MOCK_ACTIVATION_STATUS.name())) != APP_MOCKS)
      getTestContext().set(MOCK_ACTIVATION_STATUS.name(), APP_MOCKS);
  }

  /**
   * Verifies the users creation flows
   */
  @Then("the users flow is completed")
  public void theUsersFlowIsCompleted() {
    List<UserCreationResultByFanType> userCreationResultByFanTypeList =
      getTestContext().get(USER_CREATION_RESULT.name());

    Allure.step(format(
        "General Parameters -> *GameFaceId: %s *IdentityPass: %s *PaymentMethod: %s *Teammate: %s "
          + "*Vehicle: %s", GAME_FACE_ID, IDENTITY_PASS, PAYMENT_METHOD, TEAMMATE.size(), VEHICLE),
      PASSED);

    for (UserCreationResultByFanType userCreationResultByFanType :
      userCreationResultByFanTypeList) {
      Allure.step(format(
        "*******FanType*******: %s", userCreationResultByFanType.getFanType()), PASSED);

      userCreationTask.showsTheUserCreationResult(
        userCreationResultByFanType.getUserCreationResultList());
    }
  }
}