package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;
import static com.automation.lac.qa.utils.DateTimeUtils.convertTimeStampToDate;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LinkedAccount;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.TeammatesListScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.TeamMateInfoComponent;
import java.util.List;

public class TeammatesListQuestions extends TeammatesListScreen {

  private static final String LABEL = "label";

  /**
   * Validate if the fan profile teammates list is properly displayed.
   *
   * @param fanAccountType type of account
   */
  public void validateIfFanProfileTeammatesListIsProperlyDisplayed(String fanAccountType) {
    IntuitDomeAccountDto account = getFanAccountContextDataByAccountType(fanAccountType);
    IntuitDomeAccountDto updatedAccount =
      IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(account.getId());
    List<LinkedAccount> accounTeammatesList = updatedAccount.getLinkedAccounts();

    String teammatesCountLabel = getTeamMatesListTitle().getAttribute(LABEL);
    getSoftAssert().assertTrue(
      teammatesCountLabel.contains(String.valueOf(accounTeammatesList.size())),
      "Teammates list label display correct fan vehicles count");

    List<TeamMateInfoComponent> screenTeammatesList = getTeammatesList();
    getSoftAssert().assertEquals(accounTeammatesList.size(), screenTeammatesList.size(),
      "Expected amount of teammates notes is displayed");

    for (int i = 0; i < accounTeammatesList.size(); i++) {
      TeamMateInfoComponent teammateScreenNote = screenTeammatesList.get(i);
      LinkedAccount teammateApiNote = accounTeammatesList.get(i);

      final String surname = (String.format("%s %s",
        teammateApiNote.getFirstname(),
        teammateApiNote.getLastname())).toUpperCase();
      final String date =
        convertTimeStampToDate(teammateApiNote.getDateOfBirth(), "MMM dd, yyyy", "UTC");

      getSoftAssert().assertEquals(
        teammateScreenNote.getLblSurname().getAttribute(LABEL), surname,
        "Teammate surname correctly displayed on teammates list screen as: " + surname);
      getSoftAssert().assertEquals(
        teammateScreenNote.getLblChosenName().getAttribute(LABEL), teammateApiNote.getChosenName(),
        "Teammate chosen name correctly displayed on teammates list screen as: "
          + teammateApiNote.getChosenName());
      getSoftAssert().assertEquals(teammateScreenNote.getLblDateOfBirth().getAttribute(LABEL),
        "Date of birth: " + date.toUpperCase(),
        "Teammate date of birth correctly displayed on teammates list screen as: " + date);
    }
  }
}
