package com.automation.lac.qa.staffapp.mobile.tasks.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.access.SelectDeviceScreen;
import java.util.List;
import org.openqa.selenium.WebElement;

public class SelectDeviceTask extends SelectDeviceScreen {

  /**
   * select checkboxes related to specific devices ids.
   *
   * @param deviceIds List of devices ids to be selected.
   * @return SelectDeviceTask
   */
  public SelectDeviceTask selectDevicesById(List<String> deviceIds) {
    deviceIds.forEach(id -> {
      WebElement checkBoxByDeviceId = getDeviceCheckBoxByDeviceId(id);
      click(checkBoxByDeviceId, id);
    });
    return this;
  }

  /**
   * tap button to confirm devices selection.
   */
  public void confirmSelectedDevices() {
    click(getBtnConfirmSelectDeviceFilter(), "CONFIRM");
  }
}
