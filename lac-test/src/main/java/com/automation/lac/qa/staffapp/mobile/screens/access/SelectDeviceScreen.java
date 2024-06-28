package com.automation.lac.qa.staffapp.mobile.screens.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class SelectDeviceScreen extends MobileBaseScreen {

  @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "lane-description"),
    @iOSXCUITBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,"
      + "'Look for the code over each')]")})
  private WebElement selectDeviceFilterMessage;

  @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "confirm_select_lane_filter"),
    @iOSXCUITBy(accessibility = "confirm_select_idaps_filter")})
  private WebElement btnConfirmSelectDeviceFilter;

  @iOSXCUITFindBy(accessibility = "CLOSE")
  private WebElement btnClose;

  @iOSXCUITFindBy(accessibility = "select_lane_label")
  private WebElement selectLanesSidebarTitle;

  /**
   * Get Device checkbox by using device ID
   */
  public WebElement getDeviceCheckBoxByDeviceId(String idapId) {
    String locator = "//XCUIElementTypeButton[@name='StaffCheckbox_ImageStyle_" + idapId + "']";
    return getElement(By.xpath(locator));
  }
}
