package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.fanprofile;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;
import static com.automation.lac.qa.staffapp.mobile.enums.ManualAgeVerificationOption.END_OF_DAY;
import static com.automation.lac.qa.staffapp.mobile.enums.SuccessMessage.SUCCESS_AGE_VERIFICATION_ADDED;

import com.automation.lac.qa.staffapp.mobile.enums.SuccessMessage;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.FanProfileAccessQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileAccessTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FanProfileAccessStepDefinitions {

  private final FanProfileAccessQuestions fanProfileAccessQuestions =
    new FanProfileAccessQuestions();
  private final FanProfileAccessTask fanProfileAccessTask = new FanProfileAccessTask();

  @Then("the user should see fan identity information of the {string} account type properly"
    + " displayed")
  public void theFanIdentityInformationIsProperlyDisplayed(String fanAccountType) {
    fanProfileAccessQuestions.validateIfIdentitySectionAttributesAreProperlyDisplayed(
      fanAccountType);
  }

  @And("the user removes age verification note from a fan information screen")
  public void removeAgeVerificationNote() {
    fanProfileAccessTask.removeAgeVerificationNote();
  }

  /**
   * unlink fam clipper band.
   */
  @And("the user unlinks the fan clipper band")
  public void tapUnlinkClipperBand() {
    fanProfileAccessTask
      .tapUnlinkFanClipperBand()
      .confirmUnlinkFanClipperBand();
  }

  @And("the user should see the correct clipper band id of {string} account type displayed")
  public void validateIfExpectedClipperBandIdDisplayed(String fanAccountType) {
    fanProfileAccessQuestions.validateIfCorrectClipperBandIdDisplayed(fanAccountType);
  }

  @And("the user sees there is no clipper band linked to fan account")
  public void validateNoClipperBandLinked() {
    fanProfileAccessQuestions.validateIfThereIsNoClipperBandLinked();
  }

  /**
   * validate if banner message contains expected text.
   */
  @And("the user sees the expected banner message {string} displayed")
  public void expectedBannerMessageDisplayed(String message) {
    SuccessMessage successMessage = SuccessMessage.valueOf(message);
    fanProfileAccessQuestions.correctBannerMessageIsDisplayed(
      successMessage.getMessage());
  }

  /**
   * Validate age verification was done till the end of day.
   */
  @And("the user should see age verification is done till the end of current day")
  public void fanAgeVerificationIsValidTillTheEndOfCurrentDay() {
    fanProfileAccessQuestions.correctBannerMessageIsDisplayed(
        SUCCESS_AGE_VERIFICATION_ADDED.getMessage())
      .validateIfAgeVerificationManuallyDoneTillTheEndOfDay();
  }

  /**
   * Tap 'verify age manually, then select 'till end of day'.
   */
  @When("the user provides manual age verification till the end of today")
  public void selectEndOfDayManualAgeVerificationOption() {
    fanProfileAccessTask
      .tapVerifyAgeManuallyButton()
      .selectManualAgeVerificationOption(END_OF_DAY);
  }

  /**
   * performs manual age verification till the end of the day.
   */
  @When("the user performs manual age verification till the end of the day")
  public void userPerformsManualAgeVerification() {
    fanProfileAccessTask
      .tapVerifyAgeManuallyButton()
      .selectManualAgeVerificationOption(END_OF_DAY);
    fanProfileAccessQuestions.correctBannerMessageIsDisplayed(
        SUCCESS_AGE_VERIFICATION_ADDED.getMessage())
      .validateIfAgeVerificationManuallyDoneTillTheEndOfDay();
    if (!isIpad()) {
      fanProfileAccessTask.tapBackButton();
    }
  }

  /**
   * Tap on '<' button to close fan identity section on iPhone device.
   */
  @When("the user stops viewing fan identity section")
  public void theUserTapsOnTheViewFanIdentitySection() {
    if (!isIpad()) {
      fanProfileAccessTask.tapBackButton();
    }
  }

  @Then("the user should see the fan profile vehicles section of the {string} account type properly"
    + " displayed")
  public void theFanVehiclesSectionIsProperlyDisplayed(String fanAccountType) {
    fanProfileAccessQuestions.isFanInformationVehiclesSectionProperlyDisplayed(fanAccountType);
  }

  @When("the user opens the vehicles list of the fan profile")
  public void userOpenFanInformationVehiclesList() {
    fanProfileAccessTask.openFanProfileVehiclesList();
  }
}
