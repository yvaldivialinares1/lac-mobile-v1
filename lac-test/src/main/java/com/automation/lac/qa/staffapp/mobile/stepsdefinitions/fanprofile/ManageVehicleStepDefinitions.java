package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.fanprofile;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.FanProfileAccessQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.ManageVehicleQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.VehiclesListQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileAccessTask;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.ManageVehicleTask;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.VehiclesListTask;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Responsible to add/edit vehicle screen.
 */
public class ManageVehicleStepDefinitions {

  private final ManageVehicleTask manageVehicleTask = new ManageVehicleTask();
  private final ManageVehicleQuestions manageVehicleQuestions = new ManageVehicleQuestions();
  private final VehiclesListQuestions vehiclesListQuestions = new VehiclesListQuestions();
  private final VehiclesListTask vehiclesListTask = new VehiclesListTask();
  private final FanProfileAccessTask fanProfileAccessTask = new FanProfileAccessTask();
  private final FanProfileAccessQuestions fanProfileAccessQuestions =
    new FanProfileAccessQuestions();


  @Then("the user tap the button to apply changing vehicle")
  public void tapApplyChangesButton() {
    manageVehicleTask.tapApplyChangesButton();
  }

  @Then("the user should see fan vehicle details are properly displayed")
  public void validateVehicleDetailsAttributes() {
    manageVehicleQuestions.validateVehicleDetailsProperlyDisplayed();
  }

  /**
   * User sees the staff member's vehicle details properly displayed
   */
  @Then("the user sees the staff member's vehicle details properly displayed")
  public void validateVehicleDetails() {
    manageVehicleQuestions.validateStaffVehicleDetailsProperlyDisplayed();
  }

  /**
   * Add new vehicle from the vehicle list section.
   *
   * @param fanAccountType String indicating the id of fan account.
   */
  @When("the user adds a new vehicle to the {string} account")
  public void addVehicleFromVehicleList(String fanAccountType) {
    fanProfileAccessTask.openFanProfileVehiclesList();
    vehiclesListQuestions.theFanProfileVehiclesListIsProperlyDisplayed(fanAccountType);
    vehiclesListTask
      .tapAddVehicleButton()
      .manageCompleteFanVehicleDetailsForm()
      .tapAddVehicleButton();
    IntuitDomeAccountApiTask.updateFanAccountContextData(fanAccountType);
  }

  /**
   * edit vehicle details.
   */
  @Then("the user edits fan vehicle details")
  public void editFanVehicleDetails() {
    manageVehicleTask
      .tapEditVehicle()
      .manageCompleteFanVehicleDetailsForm()
      .tapApplyChangesButton();
  }

  /**
   * Edits fan vehicle info.
   */
  @When("the user edits the fan {string} vehicle details")
  public void theUserEditsTheVehicleDetails(String fanAccountType) {
    fanProfileAccessTask.openFanProfileVehiclesList();
    vehiclesListQuestions.theFanProfileVehiclesListIsProperlyDisplayed(fanAccountType);
    vehiclesListTask.selectFirstVehicleNote();
    IntuitDomeAccountDto account = getFanAccountContextDataByAccountType(fanAccountType);
    getTestContext().set(LICENCE_PLATE, account.getLicensePlates().get(0));
    manageVehicleQuestions.validateVehicleDetailsProperlyDisplayed();
    manageVehicleTask
      .tapEditVehicle()
      .manageCompleteFanVehicleDetailsForm()
      .tapApplyChangesButton();
  }

  /**
   * remove vehicle from vehicle details screen.
   */
  @Then("the user removes fan vehicle note")
  public void removeVehicleNote() {
    manageVehicleTask
      .tapRemoveVehicle()
      .tapConfirmRemoveVehiclePopup();
  }

  /**
   * remove vehicle from vehicle details screen.
   */
  @Then("the user removes fan {string} vehicle note")
  public void removeVehicleNote(String fanAccountType) {
    fanProfileAccessQuestions.isFanInformationVehiclesSectionProperlyDisplayed(fanAccountType);
    fanProfileAccessTask.openFanProfileVehiclesList();
    vehiclesListQuestions.theFanProfileVehiclesListIsProperlyDisplayed(fanAccountType);
    vehiclesListTask.selectFirstVehicleNote();
    IntuitDomeAccountDto account = getFanAccountContextDataByAccountType(fanAccountType);
    getTestContext().set(LICENCE_PLATE, account.getLicensePlates().get(0));
    manageVehicleTask
      .tapRemoveVehicle()
      .tapConfirmRemoveVehiclePopup();
  }
}
