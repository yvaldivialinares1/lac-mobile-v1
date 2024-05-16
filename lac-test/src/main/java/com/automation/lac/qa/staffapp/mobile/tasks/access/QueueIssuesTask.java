package com.automation.lac.qa.staffapp.mobile.tasks.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAvailability;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementUnavailability;

import com.automation.lac.qa.staffapp.mobile.screens.access.QueueIssuesScreen;
import com.automation.lac.qa.staffapp.mobile.screens.access.components.EventCardComponent;
import com.automation.lac.qa.utils.CustomException;

public class QueueIssuesTask extends QueueIssuesScreen {

  /**
   * open 'Select Lanes' sidebar panel
   */
  public void clickSelectLinesButton() {
    click(getBtnSelectLanes(), "SELECT LANES");
  }

  /**
   * wait while spinner indicator is displayed
   */
  public void waitForSpinnerDisappear() {
    waitForElementAvailability(getProgressBarIcon(), 5);
    waitForElementUnavailability(getProgressBarIcon(), 60);
  }

  /**
   * get event card by device id.
   *
   * @param deviceId id of the device.
   * @return EventCardComponent
   */
  public EventCardComponent getEventCardById(String deviceId) {
    return getEventCardList().stream()
      .filter(card -> card.getIssueCardDeviceId().getAttribute("label").equals(deviceId))
      .findFirst()
      .orElseThrow(() -> new CustomException("No event card found with device id as: " + deviceId));
  }

  /**
   * get event card by licence plate.
   *
   * @param licencePlate licence plate number.
   * @return EventCardComponent
   */
  public EventCardComponent getEventCardByLicencePlate(String licencePlate) {
    return getEventCardList().stream()
      .filter(
        card -> card.getIssueCardPlateValues().get(0).getAttribute("label").equals(licencePlate))
      .findFirst()
      .orElseThrow(
        () -> new CustomException("No event card found with device id as: " + licencePlate));
  }
}
