package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LinkedAccount;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfileInformationScreen;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileInformationTask;
import com.automation.lac.qa.utils.mobile.WaitActions;
import io.qameta.allure.Step;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class FanProfileInformationQuestions extends FanProfileInformationScreen {

  private static final String LABEL = "label";
  private final FanProfileInformationTask fanProfileInformationTask =
    new FanProfileInformationTask();

  /**
   * Validate fan profile information.
   */
  @Step("Validate fan profile screen visibility")
  public void isFanProfileInformationDisplayed() {
    getSoftAssert().assertTrue(getLblFanInitials().isDisplayed(), "Fan initials");
    getSoftAssert().assertTrue(getLblFanName().isDisplayed(), "Fan name");
    getSoftAssert().assertTrue(getLblFanLastName().isDisplayed(), "Fan last name");
  }

  /**
   * Validate if the fan profile basic information properly displayed.
   *
   * @param fanAccountType type of account
   */
  @Step("checking fan profile basic information is properly displayed")
  public void isFanProfileBasicInformationProperlyDisplayed(String fanAccountType) {
    IntuitDomeAccountDto intuitDomeAccount = getFanAccountContextDataByAccountType(fanAccountType);
    String expectedInitials = (intuitDomeAccount.getFirstname().charAt(0)
      + intuitDomeAccount.getLastname().substring(0, 1)).toUpperCase();
    final String expectedAddress = String.format("%s %s %s %s %s",
      intuitDomeAccount.getAddress1(),
      intuitDomeAccount.getAddress2(),
      intuitDomeAccount.getCity(),
      intuitDomeAccount.getState(),
      intuitDomeAccount.getZipcode());

    getSoftAssert().assertEquals(getLblFanInitials().getAttribute(LABEL), expectedInitials,
      "fan profile initials label is properly displayed");
    getSoftAssert().assertEquals(getLblFanName().getAttribute(LABEL),
      intuitDomeAccount.getFirstname().toUpperCase(),
      "fan profile first name label is properly displayed");
    getSoftAssert().assertEquals(
      StringUtils.deleteWhitespace(getLblFanLastName().getAttribute(LABEL)),
      intuitDomeAccount.getLastname().toUpperCase(),
      "fan profile last name label is properly displayed");
    String chosenNameLbl = getLblFanChosenName().getAttribute(LABEL).replaceFirst("^\\s+", "");
    getSoftAssert().assertEquals(chosenNameLbl, intuitDomeAccount.getChosenName(),
      "fan profile chosen name label is properly displayed");

    fanProfileInformationTask.tapSeePersonalInformation();
    getSoftAssert().assertEquals(getLblFanEmail().getAttribute(LABEL),
      intuitDomeAccount.getEmail(),
      "fan profile email label is properly displayed");
    getSoftAssert().assertEquals(StringUtils.deleteWhitespace(
        getLblFanPhoneNumber().getAttribute(LABEL)),
      intuitDomeAccount.getPhone(),
      "fan profile phone number label is properly displayed");
    getSoftAssert().assertEquals(getLblFanAddress().getAttribute(LABEL), expectedAddress,
      "fan profile address label is properly displayed");
  }

  /**
   * Validate if the fan profile teammates information properly displayed.
   *
   * @param fanAccountType type of account
   */
  @Step("checking profile teammates sections is properly displayed")
  public void isFanProfileTeammatesSectionProperlyDisplayed(String fanAccountType) {
    if (FanType.valueOf(fanAccountType).equals(FanType.MINOR_ADULT)) {
      getSoftAssert().assertFalse(
        WaitActions.isTheElementVisible(getLblTeamMatesTitle(), 3),
        "Teammates label does not displayed for account of type " + fanAccountType);
    } else {
      List<LinkedAccount> teamMatesList =
        getFanAccountContextDataByAccountType(fanAccountType).getLinkedAccounts();
      getSoftAssert().assertTrue(
        getLblTeamMatesTitle().getAttribute(LABEL).contains(String.valueOf(teamMatesList.size())),
        "Teammates label reflects expected amount of teammates linked to fan account");
    }
  }
}
