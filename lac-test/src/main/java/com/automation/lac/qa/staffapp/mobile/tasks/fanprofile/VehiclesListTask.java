package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.VehiclesListScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.VehicleInfoComponent;
import io.qameta.allure.Step;
import java.util.List;

public class VehiclesListTask extends VehiclesListScreen {

  @Step("tap add vehicle button")
  public ManageVehicleTask tapAddVehicleButton() {
    click(getBtnAddVehicle(), "add vehicle");
    return new ManageVehicleTask();
  }

  public void tapCloseVehiclesListButton() {
    click(getBtnClose(), "close");
  }

  public void tapBackButton() {
    click(getBtnBack(), "Back");
  }

  /**
   * select first vehicle note from a vehicles list.
   */
  @Step("select first vehicle note")
  public void selectFirstVehicleNote() {
    List<VehicleInfoComponent> screenVehiclesList = getVehiclesList();
    click(screenVehiclesList.get(0).getLblVehicleNickname(), "First vehicle row");
  }
}
