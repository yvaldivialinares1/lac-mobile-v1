package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementInvisible;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfileAccessScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.VehicleInfoComponent;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.mobile.WaitActions;
import io.qameta.allure.Step;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FanProfileAccessQuestions extends FanProfileAccessScreen {

  private static final String LABEL = "label";

  /**
   * Validate fan profile identity attributes information.
   */
  public void validateIfIdentitySectionAttributesAreProperlyDisplayed(String fanAccountType) {
    final FanType fanType = FanType.valueOf(fanAccountType);
    if (isIpad()) {
      getSoftAssert().assertTrue(
        getFanProfileAccessIdentityComponent().getLblIdentityPassTitle().isDisplayed(),
        "Identity pass displayed on fan information screen");
    }
    getSoftAssert().assertTrue(
      getFanProfileAccessIdentityComponent().getLblGameFaceIdTitle().isDisplayed(),
      "Game Face ID note is displayed on fan information screen");
    getSoftAssert().assertTrue(
      getFanProfileAccessIdentityComponent().getBtnRemoveGameFace().isDisplayed(),
      "Remove Game Face ID button is displayed on fan information screen");
    switch (fanType) {
      case ADULT:
        getSoftAssert().assertTrue(
          getFanProfileAccessIdentityComponent().getLblAgeVerificationTitle().isDisplayed(),
          "Age verification label is displayed on fan information screen");
        getSoftAssert().assertTrue(
          getFanProfileAccessIdentityComponent().getBtnRemoveAgeVerification().isDisplayed(),
          "Remove Age verification button is displayed on fan information screen");
        break;
      case MINOR_ADULT:
        getSoftAssert().assertTrue(
          isTheElementInvisible(getFanProfileAccessIdentityComponent().getLblAgeVerificationTitle(),
            1),
          "Age verification label isn't displayed on fan information screen");
        break;
      default:
        throw new CustomException("Validation steps are not implemented for " + fanType);
    }
  }

  /**
   * Validate fan profile age verification is manually verified till the end of current day.
   */
  @Step("Validate age verification manually done till end of the day")
  public void validateIfAgeVerificationManuallyDoneTillTheEndOfDay() {
    String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    getSoftAssert().assertEquals(
      getFanProfileAccessIdentityComponent().getLblAgeVerificationState().getAttribute(LABEL),
      "VERIFIED MANUALLY",
      "Age verification state contains expected value");
    getSoftAssert().assertTrue(
      getFanProfileAccessIdentityComponent().getLblAgeVerificationSource().getAttribute(LABEL)
        .contains(formattedDate),
      "Age verification source label contains correct date");

  }

  /**
   * validate if correct banner message is displayed.
   *
   * @return FanProfileAccessQuestions
   */
  @Step("checking banner message: {message}")
  public FanProfileAccessQuestions correctBannerMessageIsDisplayed(String message) {
    getSoftAssert().assertEquals(
      getBannerMessageComponent().getBannerMessageText(), message,
      "Banner message contains expected text as " + message);
    getSoftAssert().assertTrue(getBannerMessageComponent().isBannerMessageDisappearedInFewSeconds(),
      "Banner message disappeared in few seconds");
    return this;
  }

  /**
   * validate if correct clipper band id is displayed.
   *
   * @param fanAccountType account type
   */
  public void validateIfCorrectClipperBandIdDisplayed(String fanAccountType) {
    IntuitDomeAccountDto account = getFanAccountContextDataByAccountType(fanAccountType);
    getSoftAssert().assertEquals(
      getFanProfileClipperBandComponent().getClipperBandId().getAttribute(LABEL),
      "Clipperband ID: " + account.getNfcIds().stream().findFirst().orElseThrow(),
      "Correct clipper band id value is displayed");
  }

  /**
   * validate if there is no clipper band linked to fan account.
   */
  public void validateIfThereIsNoClipperBandLinked() {
    if (!isIpad()) {
      getSoftAssert().assertTrue(
        getFanProfileClipperBandComponent().getBtnLinkNfc().isDisplayed(),
        "Link nfc button is displayed");
    }
    getSoftAssert().assertTrue(
      WaitActions.isTheElementVisible(getFanProfileClipperBandComponent().getBtnLinkManually(), 5),
      "Link clipper band manually button is displayed");
    getSoftAssert().assertEquals(
      getFanProfileClipperBandComponent().getMsgNoClipperBandLinked().getAttribute(LABEL),
      "There is no Clipperband linked yet",
      "'There is no Clipperband linked yet' message is displayed under clipper band section");
  }

  /**
   * Validate if the fan profile vehicles section properly displayed.
   *
   * @param fanAccountType type of account
   */
  @Step("checking fan information vehicles section is properly displayed")
  public void isFanInformationVehiclesSectionProperlyDisplayed(String fanAccountType) {
    IntuitDomeAccountDto account = IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(
      getFanAccountContextDataByAccountType(fanAccountType).getId());
    List<LicensePlateDto> accoundVehiclesList = account.getLicensePlates();

    String vehiclesCountLabel =
      getFanProfileAccessVehiclesComponent().getLblFanVehicles().getAttribute(LABEL);
    getSoftAssert().assertTrue(
      vehiclesCountLabel.contains(String.valueOf(accoundVehiclesList.size())),
      "Vehicles label display correct fan vehicles count");

    if (isIpad()) {
      LicensePlateDto firstPlate = accoundVehiclesList.stream().findFirst().orElseThrow();
      final String state = LicensePlateApiTask.getStateName(firstPlate.getState());
      VehicleInfoComponent vehicleInfoNote =
        getFanProfileAccessVehiclesComponent().getVehiclesList().stream().findFirst().orElseThrow();
      getSoftAssert().assertEquals(
        vehicleInfoNote.getLblVehicleNickname().getAttribute(LABEL), firstPlate.getNickname(),
        "Licence nickname correctly displayed on fan information screen");
      getSoftAssert().assertTrue(
        vehicleInfoNote.getLblVehicleRowData().getAttribute(LABEL)
          .contains(firstPlate.getLicensePlate()),
        "Vehicles note contains expected plate number on fan information screen");
      getSoftAssert().assertTrue(
        vehicleInfoNote.getLblVehicleRowData().getAttribute(LABEL).contains(state),
        "Vehicles note contains expected plate number");
    }
  }
}
