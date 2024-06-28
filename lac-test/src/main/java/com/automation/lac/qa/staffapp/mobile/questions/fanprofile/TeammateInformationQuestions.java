package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.TEAMMATE_NOTE;
import static com.automation.lac.qa.utils.DateTimeUtils.convertTimeStampToDate;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LinkedAccount;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.TeammateInformationScreen;

public class TeammateInformationQuestions extends TeammateInformationScreen {

  private static final String LABEL = "label";

  /**
   * Validate if teammate information is properly displayed.
   */
  public void validateIfTeamMateInformationIsProperlyDisplayed() {
    final LinkedAccount teammate = getTestContext().get(TEAMMATE_NOTE);
    final IntuitDomeAccountDto account =
      IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(teammate.getId());

    final String surname = (String.format("%s %s",
      teammate.getFirstname(), teammate.getLastname())).toUpperCase();
    final String date = convertTimeStampToDate(teammate.getDateOfBirth(), "MMM dd, yyyy", "UTC");

    getSoftAssert().assertEquals(getLblSurname().getAttribute(LABEL), surname,
      "Teammate surname correctly displayed on teammate information screen as: " + surname);
    getSoftAssert().assertEquals(getLblChosenName().getAttribute(LABEL), teammate.getChosenName(),
      "Teammate chosen name correctly displayed on teammate information screen as: "
        + teammate.getChosenName());
    getSoftAssert().assertEquals(getLblDateOfBirth().getAttribute(LABEL),
      "Date of birth: " + date.toUpperCase(),
      "Teammate date of birth correctly displayed on teammates info screen as: " + date);
    getSoftAssert().assertEquals(
      getFanProfileClipperBandComponent().getClipperBandId().getAttribute(LABEL),
      "Clipperband ID: " + account.getNfcIds().stream().findFirst().orElseThrow(),
      "Correct clipper band id value is displayed for teammate note");
  }
}
