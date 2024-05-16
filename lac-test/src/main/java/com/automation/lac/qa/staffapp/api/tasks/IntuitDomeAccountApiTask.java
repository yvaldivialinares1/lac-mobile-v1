package com.automation.lac.qa.staffapp.api.tasks;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.staffapp.api.models.identity.AccountRegistrationResponse;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.NewIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.TicketingIdRequestDto;
import com.automation.lac.qa.staffapp.api.services.identity.IntuitDomeAccountService;
import com.automation.lac.qa.staffapp.api.utils.TestDataUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IntuitDomeAccountApiTask {

  /**
   * Register new intuit Dome Account
   *
   * @param type The type of fan which determines the age range for the birthdate.
   * @return AccountRegistrationResponse data
   */
  public AccountRegistrationResponse registerNewIntuitDomeAccount(FanType type) {
    NewIntuitDomeAccountDto randomNewIntuitDomeAccount =
      TestDataUtils.createRandomNewIntuitDomeAccountDto(type);

    return IntuitDomeAccountService.registerNewIntuitDomeAccount(randomNewIntuitDomeAccount);
  }

  /**
   * Retrieves latest update IntuitDomeAccount data from API.
   *
   * @param accountId String indicating the id of fan account.
   * @return IntuitDomeAccountDto
   */
  public IntuitDomeAccountDto getUpdatedIntuitDomeAccountApiData(String accountId) {
    return IntuitDomeAccountService.findIntuitDomeAccountById(accountId);
  }

  /**
   * Add ticket mater id to an existing fan account.
   *
   * @param accountId      String indicating the id of fan account.
   * @param ticketMasterId String indicating the ticket master id.
   */
  public void addTicketingIdToIntuitDomAccount(String accountId, String ticketMasterId) {
    TicketingIdRequestDto request =
      TicketingIdRequestDto.builder()
        .ticketingId(ticketMasterId)
        .intuitDomeAccountId(accountId)
        .build();
    IntuitDomeAccountService.addTicketingIdToIntuitDomeAccount(request);
  }
}
