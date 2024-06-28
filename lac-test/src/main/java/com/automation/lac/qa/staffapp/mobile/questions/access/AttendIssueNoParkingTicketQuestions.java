package com.automation.lac.qa.staffapp.mobile.questions.access;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.ADULT_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.mobile.screens.access.AttendIssueNoParkingTicketScreen;

public class AttendIssueNoParkingTicketQuestions extends AttendIssueNoParkingTicketScreen {

  private static final String LABEL_ATTRIBUTE = "label";

  /**
   * Validate employee see the vehicle added to fan account but fan has no parking ticket.
   */
  public void validateVehicleIsAddedButFanHasNoParkingTicket() {
    final IntuitDomeAccountDto accountDto = getTestContext().get(ADULT_INTUIT_DOME_ACCOUNT);
    String name = (accountDto.getFirstname() + " " + accountDto.getLastname()).toUpperCase();
    getSoftAssert()
      .assertEquals(getSecondaryTitle().getAttribute(LABEL_ATTRIBUTE),
        "This fan does not have a parking ticket",
        "Secondary title value is expected");
    getSoftAssert()
      .assertEquals(getFanName().getAttribute(LABEL_ATTRIBUTE), name,
        "fan name value is expected");
    getSoftAssert()
      .assertEquals(getFanEmail().getAttribute(LABEL_ATTRIBUTE), accountDto.getEmail(),
        "Fan email value is expected");
  }
}
