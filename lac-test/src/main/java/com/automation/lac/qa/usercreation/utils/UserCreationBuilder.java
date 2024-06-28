package com.automation.lac.qa.usercreation.utils;

import static com.automation.lac.qa.faker.AleatoryData.createRandomInfoList;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.ACCOUNT_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.GAME_FACE_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IDENTITY_PASS_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PAYMENT_METHODS_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PHONE_VERIFICATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.TEAM_MATE_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.TICKET_PURCHASE_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.VEHICLES_CREATION_RESULT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.UserCreationConstant.FANS_NUMBER;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.faker.models.UserInfoByFanType;
import com.automation.lac.qa.usercreation.models.UserCreationResult;
import com.automation.lac.qa.usercreation.models.UserCreationResultByFanType;
import io.qameta.allure.model.Status;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserCreationBuilder {

  /**
   * This method build the UserCreationResult
   * @param email this is the user email
   * @param password this is the user password
   * @param status this the user creation result
   * @return UserCreationResult object
   */
  public static UserCreationResult userCreationResultBuilder(String email, String password,
                                                             Status status) {
    return UserCreationResult.builder()
      .email(email)
      .password(password)
      .status(status)
      .accountCreationResult(getCreationResult(ACCOUNT_CREATION_RESULT.name()))
      .phoneVerificationResult(getCreationResult(PHONE_VERIFICATION_RESULT.name()))
      .gameFaceIdResult(getCreationResult(GAME_FACE_CREATION_RESULT.name()))
      .identityPassResult(getCreationResult(IDENTITY_PASS_CREATION_RESULT.name()))
      .paymentMethodResult(getCreationResult(PAYMENT_METHODS_CREATION_RESULT.name()))
      .teamMateCreationResult(getCreationResult(TEAM_MATE_CREATION_RESULT.name()))
      .vehicleCreationResult(getCreationResult(VEHICLES_CREATION_RESULT.name()))
      .ticketPurchaseResult(getCreationResult(TICKET_PURCHASE_RESULT.name()))
      .build();
  }

  /**
   * This method build the UserInfo list grouped by FanType
   * @param fanType the type of fan to be created
   * @return UserInfoByFanType object
   */
  public static UserInfoByFanType userInfoByFanTypeBuilder(FanType fanType) {
    return UserInfoByFanType.builder()
      .fanType(fanType)
      .usersInfo(createRandomInfoList(FANS_NUMBER <= 10 ? FANS_NUMBER : 10, fanType))
      .build();
  }

  /**
   * This method build the UserCreationResult list grouped by FanType
   * @param fanType                the type of fan to be created
   * @param userCreationResultList the user creation result
   * @return UserCreationResultByFanType object
   */
  public static UserCreationResultByFanType userCreationResultByFanBuilder(FanType fanType,
                                                                           List<UserCreationResult>
                                                                       userCreationResultList) {
    return UserCreationResultByFanType.builder()
      .fanType(fanType)
      .userCreationResultList(userCreationResultList)
      .build();
  }

  /**
   * Get a creation result object
   * @return a generic object or a null one in case there are no results on the context
   */
  public static <T> T getCreationResult(String key) {
    try {
      return (T) getTestContext().get(key);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Remove a last creation result for the next user creation
   */
  public static void removeCreationResult(String key) {
    if (getCreationResult(key) != null)
      getTestContext().remove(key);
  }
}