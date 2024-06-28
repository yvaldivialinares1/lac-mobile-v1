package com.automation.lac.qa.staffapp.api.tasks;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.services.identity.StaffMemberAccountService;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StaffMemberAccountApiTask {

  /**
   * Retrieves latest update Staff member data from API.
   *
   * @param accountId String indicating the id of fan account.
   * @return IntuitDomeAccountDto
   */
  public static IntuitDomeAccountDto getUpdatedStaffMemberAccountApiData(String accountId) {
    return StaffMemberAccountService.findStaffMemberAccountById(accountId);
  }
}
