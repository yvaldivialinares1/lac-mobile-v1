package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.ManageVehicleScreen;
import io.qameta.allure.Step;

public class ManageVehicleQuestions extends ManageVehicleScreen {

  private static final String LABEL = "label";

  /**
   * Validate that vehicle details are properly displayed on view vehicle screen.
   */
  @Step("validate vehicle details are properly displayed")
  public void validateVehicleDetailsProperlyDisplayed() {
    final LicensePlateDto licensePlate = getTestContext().get(LICENCE_PLATE);
    final String state = LicensePlateApiTask.getStateName(licensePlate.getState());

    getSoftAssert().assertEquals(getVehicleNickNameValue().getAttribute(LABEL),
      licensePlate.getNickname(), "Licence nickname is properly displayed on view vehicle screen");
    getSoftAssert().assertEquals(getVehiclePlateValue().getAttribute(LABEL),
      licensePlate.getLicensePlate(), "Licence plate is properly displayed on view vehicle screen");
    getSoftAssert().assertEquals(getVehicleStateValue().getAttribute(LABEL), state,
      "Licence state is properly displayed on view vehicle screen");
    getSoftAssert().assertEquals(getVehicleMakeValue().getAttribute(LABEL), licensePlate.getMake(),
      "Licence make is properly displayed on view vehicle screen");
    getSoftAssert().assertEquals(getVehicleModelValue().getAttribute(LABEL),
      licensePlate.getModel(), "Licence model is properly displayed on view vehicle screen");
    getSoftAssert().assertEquals(getVehicleColorValue().getAttribute(LABEL).toLowerCase(),
      licensePlate.getColor(), "Licence color is properly displayed on view vehicle screen");
  }

  /**
   * Validate that vehicle details are properly displayed on view vehicle screen.
   */
  public void validateStaffVehicleDetailsProperlyDisplayed() {
    final LicensePlateDto licensePlate = getTestContext().get(LICENCE_PLATE);
    final String state = LicensePlateApiTask.getStateName(licensePlate.getState());

    getSoftAssert().assertEquals(getVehiclePlateValue().getAttribute(LABEL),
      licensePlate.getLicensePlate(), "Licence plate is properly displayed on view vehicle screen");
    getSoftAssert().assertEquals(getVehicleStateValue().getAttribute(LABEL), state,
      "Licence state is properly displayed on view vehicle screen");
  }
}
