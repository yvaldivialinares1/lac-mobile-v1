package com.automation.lac.qa.fanapp.api.tasks.identity;

import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.fanapp.api.models.identity.FindIntuitDomeAccountByIdResponse.LicensePlatesDto;
import static com.automation.lac.qa.fanapp.api.models.identity.FindIntuitDomeAccountByIdResponse.LinkedIntuitDomeAccountDto;
import static com.automation.lac.qa.fanapp.api.services.identity.FindIntuitDomeAccountByIdService.getIntuitDomeAccountInformation;
import static com.automation.lac.qa.fanapp.api.services.identity.LicensePlateService.deleteLicensePlate;
import static com.automation.lac.qa.fanapp.api.services.identity.TeammateAccountService.deleteTeammateAccount;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.USER_ACCESS_TOKEN;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.api.models.identity.AccountIdRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.AccountIdResponse;
import com.automation.lac.qa.fanapp.api.models.identity.DeleteLicensePlateRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.DeleteTeammateRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.FindIntuitDomeAccountByIdResponse;
import com.automation.lac.qa.fanapp.api.services.identity.IdentityService;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@UtilityClass
public class IdentityTask {

  /**
   * get the Access Token for specific user
   *
   * @param userInfo user info tobe filled with AccountInfo
   * @return access token
   */
  public static String getAccessToken(UserInfo userInfo) {
    if (StringUtils.isEmpty(getTestContext().getOrDefault(USER_ACCESS_TOKEN, ""))) {
      AccountIdRequestDto requestBody =
        AccountIdRequestDto.builder()
          .email(userInfo.getAccountInfo().getEmail())
          .password(userInfo.getAccountInfo().getPassword())
          .hostOs(platformName.toUpperCase())
          .build();
      AccountIdResponse accountIdResponse = IdentityService.intuitDomeLogin(requestBody);
      if (accountIdResponse != null) {
        String accessToken = accountIdResponse.getAccessToken();
        getTestContext().set(USER_ACCESS_TOKEN, accessToken);
        return accessToken;
      } else return "";
    }
    return getTestContext().get(USER_ACCESS_TOKEN);
  }

  /**
   * get the Access Token for specific user with retry
   *
   * @param userInfo user info tobe filled with AccountInfo
   * @return access token
   */
  public static String getAccessTokenWithRetry(UserInfo userInfo) {
    String accessToken = getAccessToken(userInfo);
    if (StringUtils.isEmpty(accessToken)) {
      return getAccessToken(userInfo);
    }
    return accessToken;
  }

  /**
   * Retrieves account information using user ID and deltes
   * @param userId to delete information from
   */
  public static void deleteVehiclesAndTeammates(String userId) {
    FindIntuitDomeAccountByIdResponse response = getIntuitDomeAccountInformation(userId);
    List<LicensePlatesDto> vehiclesList = response.getLicensePlates();
    List<LinkedIntuitDomeAccountDto> teammatesList = response.getLinkedAccounts();

    if (!vehiclesList.isEmpty()) {
      for (LicensePlatesDto licensePlatesDto : vehiclesList) {
        DeleteLicensePlateRequestDto requestBody = DeleteLicensePlateRequestDto.builder()
          .id(licensePlatesDto.getId())
          .licensePlate(licensePlatesDto.getLicensePlate())
          .state(licensePlatesDto.getState())
          .accountId(userId)
          .build();
        deleteLicensePlate(requestBody);
      }
    }

    for (LinkedIntuitDomeAccountDto linkedAccounts : teammatesList) {
      if (!linkedAccounts.isInactive()) {
        DeleteTeammateRequestDto requestBody = DeleteTeammateRequestDto.builder()
          .intuitDomeAccountId(userId)
          .miniIntuitDomeAccountId(linkedAccounts.getId())
          .build();
        deleteTeammateAccount(requestBody);
      }
    }
  }
}