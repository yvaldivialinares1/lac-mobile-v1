package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class VehicleInfoComponent extends Widget {

  @iOSXCUITFindBy(id = "vehicle_nick_name")
  private WebElement lblVehicleNickname;

  @iOSXCUITFindBy(id = "vehicle_row_data")
  private WebElement lblVehicleRowData;

  protected VehicleInfoComponent(WebElement element) {
    super(element);
  }
}
