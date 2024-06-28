package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeIntoListToElementWithCondition;

import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.ManageVehicleScreen;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class ManageVehicleTask extends ManageVehicleScreen {

  /**
   * select vehicle state from dropDown list.
   *
   * @param state state name value
   * @return ManageVehicleTask
   */
  private ManageVehicleTask selectVehicleState(String state) {
    click(getFrmInputVehicleState(), "activate email input field");

    WebElement stateElement = swipeIntoListToElementWithCondition(
      getDropDownStates(), SwipeDirections.DOWN_TO_UP,
      webElement -> webElement.getAttribute("label").equals(state));

    click(stateElement, state);
    return this;
  }

  /**
   * select vehicle state from dropDown list.
   *
   * @return ManageVehicleTask
   */
  @Step("complete fan vehicle details form")
  public ManageVehicleTask manageCompleteFanVehicleDetailsForm() {
    final LicensePlateDto licensePlate = LicensePlateApiTask.generateUnknownLicencePlate();
    final String state = LicensePlateApiTask.getStateName(licensePlate.getState());

    getFrmInputVehicleNickName().clear();
    sendKeys(getFrmInputVehicleNickName(), licensePlate.getNickname(), "vehicle nickname");
    getFrmInputVehiclePlate().clear();
    sendKeys(getFrmInputVehiclePlate(), licensePlate.getLicensePlate(), "vehicle plate");
    getFrmInputVehicleMake().clear();
    sendKeys(getFrmInputVehicleMake(), licensePlate.getMake(), "vehicle make");
    getFrmInputVehicleModel().clear();
    sendKeys(getFrmInputVehicleModel(), licensePlate.getModel(), "vehicle model");
    getFrmInputVehicleColor().clear();
    sendKeys(getFrmInputVehicleColor(), licensePlate.getColor(), "vehicle color");
    selectVehicleState(state);
    return this;
  }

  @Step("tap add vehicle button")
  public void tapAddVehicleButton() {
    click(getBtnAddVehicle(), "add vehicle");
  }

  @Step("tap apply changes button")
  public void tapApplyChangesButton() {
    click(getBtnApplyChanges(), "apply changes");
  }

  @Step("tap on edit vehicle")
  public ManageVehicleTask tapEditVehicle() {
    click(getBtnEditVehicle(), "edit");
    return this;
  }

  @Step("tap on remove vehicle button")
  public ManageVehicleTask tapRemoveVehicle() {
    click(getBtnRemoveVehicle(), "remove");
    return this;
  }

  @Step("tap on confirm remove vehicle")
  public void tapConfirmRemoveVehiclePopup() {
    click(getRemoveVehicleAlertComponent().getBtnConfirmRemove(), "yes, remove");
  }
}
