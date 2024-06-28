package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class QueueDetailsScreen extends MobileBaseScreen {

  @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "SELECT IDAPS"),
    @iOSXCUITBy(accessibility = "SELECT LANES")})
  private WebElement sideBarSelectDeviceButton;

  @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "queue_details_no_idap_selected_title"),
    @iOSXCUITBy(accessibility = "queue_details_no_lanes_selected_title")})
  private WebElement noDeviceSelectedIntoQueueDetailsMessageTitle;

  @iOSXCUITFindAll({
    @iOSXCUITBy(accessibility = "//XCUIElementTypeStaticText" + "[@name='queue_details_idap_id']"),
    @iOSXCUITBy(accessibility = "//XCUIElementTypeStaticText[@name='queue_details_lane_id']")})
  private List<WebElement> listSelectedDevices;

  @iOSXCUITFindBy(accessibility = "queue_details_area_title")
  private WebElement queueDetailsAreaTitle;

  @iOSXCUITFindBy(accessibility = "queue_details_title")
  private WebElement queueDetailsTitle;

  @iOSXCUITFindBy(accessibility = "queue_details_close_button")
  private WebElement queueDetailsCloseButton;

  @iOSXCUITFindBy(accessibility = "queue_details_my_view_title")
  private WebElement myViewDetailsTitle;
}
