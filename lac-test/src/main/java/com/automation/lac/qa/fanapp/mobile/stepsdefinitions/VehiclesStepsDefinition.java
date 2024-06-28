package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.VEHICLES_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import com.automation.lac.qa.fanapp.mobile.questions.RegisteredVehiclesQuestion;
import com.automation.lac.qa.fanapp.mobile.tasks.myvehicles.AddVehicleTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myvehicles.RegisteredVehicleTask;
import io.cucumber.java.en.And;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VehiclesStepsDefinition {

  private final AddVehicleTask addVehicleTask = new AddVehicleTask();
  private final RegisteredVehicleTask registeredVehicleTask = new RegisteredVehicleTask();
  private final RegisteredVehiclesQuestion vehiclesQuestion = new RegisteredVehiclesQuestion();

  /**
   * Add a given number of vehicles
   * When user doesn't have vehicles the add vehicle process begins in NoRegisteredVehiclesScreen
   * From the second vehicle it has to be added from RegisteredVehiclesScreen
   *
   * @param numberOfVehicles integer
   */
  @And("the user adds {int} vehicles")
  public void theUserAddsADefinedNumberOfVehicles(int numberOfVehicles) {
    List<VehicleInfo> vehicleList = getTestContext().get(VEHICLES_INFO.name());
    List<VehicleInfo> vehiclesToAdd = vehicleList.stream().limit(numberOfVehicles).toList();

    addVehicleTask.addVehicleInformation(vehiclesToAdd);
    getTestContext().set(VEHICLES_INFO.name(), vehiclesToAdd);
  }

  /**
   * Sees the updated list of vehicles
   */
  @And("the user sees the updated list of vehicles")
  public void theUserSeesTheUpdatedListOfVehicles() {
    List<VehicleInfo> vehicleList = getTestContext().get(VEHICLES_INFO.name());
    vehiclesQuestion.listOfVehiclesMatchExpected(vehicleList);
  }

  @And("the user navigates back from my vehicles to my profile")
  public void navigateBackToMyProfile() {
    registeredVehicleTask.clickBack();
  }
}