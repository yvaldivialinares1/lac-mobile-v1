package com.automation.lac.qa.staffapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.INTUIT_DOME_ACCOUNT_1;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsStep {

  /**
   * Initiate a fan account with a specific amount of registered vehicles.
   *
   * @param numberOfVehicles int indication the expected amount of vehicles added to an account.
   */
  @Given("a fan with a {int} registered vehicle data")
  public void fanWithSpecificAmountOfRegisteredVehicles(int numberOfVehicles) {
    String id = IntuitDomeAccountApiTask.registerNewIntuitDomeAccount(FanType.ADULT)
      .getIntuitDomeAccount().getId();

    LicensePlateApiTask.registerLicensePlates(id, numberOfVehicles);

    IntuitDomeAccountDto intuitDomeAccount =
      IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(id);

    getTestContext().set(LICENCE_PLATE, intuitDomeAccount.getLicensePlates().get(0));
    getTestContext().set(INTUIT_DOME_ACCOUNT_1, intuitDomeAccount);
  }

  /**
   * Generate not linked licence plate.
   */
  @Given("there is a car with unknown licence plate")
  public void carWithUnknownLicencePlate() {
    LicensePlateDto licensePlateDto = LicensePlateApiTask.generateUnknownLicencePlate();
    getTestContext().set(LICENCE_PLATE, licensePlateDto);
  }
}