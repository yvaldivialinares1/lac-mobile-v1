package com.automation.lac.qa.staffapp.mobile.tasks.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeIntoListToElementWithCondition;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.staffapp.mobile.screens.access.AttendIssuePlateNotReadScreen;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import org.openqa.selenium.WebElement;

public class AttendIssuePlateNotReadTask extends AttendIssuePlateNotReadScreen {

  /**
   * enter text in vehicle license plate input field.
   *
   * @param plate String plate value
   * @return AttendIssuePlateNotReadTask
   */
  public AttendIssuePlateNotReadTask enterVehicleLicensePlate(String plate) {
    waitForElementToBeClickable(getVehicleLicensePlateInputField(), 3);
    sendKeys(getVehicleLicensePlateInputField(), plate, plate);
    return this;
  }

  /**
   * select vehicle state from dropDown list.
   *
   * @param state String state value
   * @return AttendIssuePlateNotReadTask
   */
  public AttendIssuePlateNotReadTask selectVehicleState(String state) {
    click(getBtnVehicleStateExpandDropDown(), "activate email input field");

    WebElement stateElement = swipeIntoListToElementWithCondition(
      getDropDownStates(), SwipeDirections.DOWN_TO_UP,
      webElement -> webElement.getAttribute("label").equals(state));

    click(stateElement, state);
    waitForElementToBeClickable(getBtnContinue(), 3);
    click(getBtnContinue(), "continue");
    return this;
  }

  /**
   * tap primary button.
   *
   * @return AttendIssuePlateNotReadTask
   */
  public AttendIssuePlateNotReadTask tapPrimaryButton() {
    final String name = getBtnPrimary().getAttribute("label");
    click(getBtnPrimary(), name);
    return this;
  }
}
