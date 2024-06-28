package com.automation.lac.qa.staffapp.mobile.tasks.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.access.QueueIssuesScreen;
import com.automation.lac.qa.staffapp.mobile.screens.access.components.EventCardComponent;
import com.automation.lac.qa.utils.CustomException;

public class QueueIssuesTask extends QueueIssuesScreen {

  private static final String LABEL = "label";

  /**
   * open 'Select Lanes' sidebar panel
   */
  public void clickSelectLinesButton() {
    click(getBtnSelectLanes(), "select lines");
  }

  /**
   * get event card by device id.
   *
   * @param deviceId id of the device.
   * @return EventCardComponent
   */
  public EventCardComponent getEventCardById(String deviceId) {
    return getEventCardList().stream()
      .filter(card -> card.getIssueCardDeviceId().getAttribute(LABEL).equals(deviceId))
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
        card -> card.getIssueCardPlateValues().get(0).getAttribute(LABEL).equals(licencePlate))
      .findFirst()
      .orElseThrow(
        () -> new CustomException("No event card found with device id as: " + licencePlate));
  }

  /**
   * identify event card by device id and tap primary button.
   *
   * @param deviceId id of the device.
   * @return QueueIssuesTask
   */
  public QueueIssuesTask tapEventCardPrimaryButton(String deviceId) {
    EventCardComponent eventCard = getEventCardById(deviceId);
    final String label = eventCard.getBtnPrimary().getAttribute(LABEL);
    click(eventCard.getBtnPrimary(), label);
    return this;
  }

  /**
   * extract the banner message text.
   *
   * @return String text value of banner message
   */
  public String getBannerMessageText() {
    return getBannerMessageComponent().getBannerMessageText();
  }
}
