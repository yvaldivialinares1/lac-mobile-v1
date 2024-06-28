package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.fanprofile;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.staffapp.constants.ServiceConstants.STAFF_ID;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.api.tasks.StaffMemberAccountApiTask;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.VehiclesListQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.VehiclesListTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VehicleListStepDefinitions {

  private final VehiclesListTask vehiclesListTask = new VehiclesListTask();
  private final VehiclesListQuestions vehiclesListQuestions = new VehiclesListQuestions();

  /**
   * Add new vehicle from the vehicle list section.
   *
   * @param fanAccountType String indicating the id of fan account.
   */
  @When("the user adds vehicle to the fan {string} account from fan vehicles list")
  public void addVehicleFromVehicleList(String fanAccountType) {
    vehiclesListTask
      .tapAddVehicleButton()
      .manageCompleteFanVehicleDetailsForm()
      .tapAddVehicleButton();
    IntuitDomeAccountApiTask.updateFanAccountContextData(fanAccountType);
  }

  /**
   * select the first vehicle note from the vehicles list.
   */
  @And("the user selects the first vehicle note of {string} account type from fan vehicles list")
  public void tapFirstVehicleNoteFromVehiclesList(String fanAccountType) {
    vehiclesListTask.selectFirstVehicleNote();
    IntuitDomeAccountDto account = getFanAccountContextDataByAccountType(fanAccountType);
    getTestContext().set(LICENCE_PLATE, account.getLicensePlates().get(0));
  }

  @Then("the user should see the fan profile vehicles list of the {string} account type properly"
    + " displayed")
  public void theFanProfileVehiclesListIsProperlyDisplayed(String fanAccountType) {
    vehiclesListQuestions.theFanProfileVehiclesListIsProperlyDisplayed(fanAccountType);
  }

  /**
   * tap the button to close fan vehicle list.
   */
  @And("the user taps the button to close the fan vehicle list")
  public void userCloseFanInformationVehiclesList() {
    if (isIpad()) {
      vehiclesListTask.tapCloseVehiclesListButton();
    } else vehiclesListTask.tapBackButton();
  }

  /**
   * select the first vehicle from the vehicles list.
   */
  @And("the user selects the first vehicle from the staff vehicles list")
  public void tapFirstVehicleFromStaffVehiclesList() {
    vehiclesListTask.selectFirstVehicleNote();
    IntuitDomeAccountDto staffMemberAccount = StaffMemberAccountApiTask
      .getUpdatedStaffMemberAccountApiData(STAFF_ID);
    getTestContext().set(LICENCE_PLATE, staffMemberAccount.getLicensePlates().get(0));
  }

  @Then("the user sees staff profile vehicles list is properly displayed")
  public void theStaffProfileVehiclesListIsProperlyDisplayed() {
    vehiclesListQuestions.theStaffProfileVehiclesListIsProperlyDisplayed(STAFF_ID);
  }
}