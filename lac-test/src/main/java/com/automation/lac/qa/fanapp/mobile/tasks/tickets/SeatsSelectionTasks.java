package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ACCESSIBLE_TOGGLE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DECREASE_SEATS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.INCREASE_SEATS;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.tickets.SeatsSelectionScreen;

public class SeatsSelectionTasks extends SeatsSelectionScreen {

  /**
   * @return Selected Seat Count
   */
  public int getDefaultSeatsCount() {
    return Integer.parseInt(getSelectedSeatCountFromScreen().getText());
  }

  /**
   * Select Seat Count Increase Button
   */
  public void updateSeatsToPurchase(int totalSeat) {
    if (totalSeat != getDefaultSeatsCount() && getDefaultSeatsCount() != 1) {
      if (totalSeat < getDefaultSeatsCount()) {
        clickOnDecreaseButton(getDefaultSeatsCount() - totalSeat);
      } else if (totalSeat > getDefaultSeatsCount()) {
        clickOnIncreaseButton(totalSeat - getDefaultSeatsCount());
      }
    }
  }

  /**
   * Select click On Increase Button
   */
  public void clickOnIncreaseButton(int totalSeat) {
    for (int i = 0; i < totalSeat; i++) {
      click(getIncreaseSeatsByOneTitle(), INCREASE_SEATS.getValue());
    }
  }

  /**
   * Select click On Decrease Button
   */
  public void clickOnDecreaseButton(int totalSeat) {
    for (int i = 0; i < totalSeat; i++) {
      click(getDecreaseSeatsByOneTitle(), DECREASE_SEATS.getValue());
    }
  }

  /**
   * click On Accessible Seat Button
   */
  public void clickOnAccessibleSeatButton() {
    click(getAccessibleSeatButton(), ACCESSIBLE_TOGGLE.getValue());
  }
}
