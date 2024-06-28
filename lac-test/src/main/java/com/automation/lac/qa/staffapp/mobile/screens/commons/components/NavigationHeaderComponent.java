package com.automation.lac.qa.staffapp.mobile.screens.commons.components;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.clickOnCoordinates;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class NavigationHeaderComponent extends Widget {

  @iOSXCUITFindBy(id = "button_Titleemergency_contact_icon")
  private WebElement btnEmergency;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='manual_search_icon' or @name='search_Title' "
      + "or @name='button_Titlemanual_search_icon']")
  private WebElement btnSearch;

  @iOSXCUITFindBy(accessibility = "button_Titleqr_code_search_icon")
  private WebElement btnQrCodeSearch;

  @iOSXCUITFindBy(accessibility = "button_Titlebiometrics_search_icon")
  private WebElement btnBioMetricSearch;

  @iOSXCUITFindAll({
    @iOSXCUITBy(id = "add_user_icon"),
    @iOSXCUITBy(id = "button_Titleadd_user_icon")
  })
  private WebElement btnAddUser;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'button_Titlenavigation_notifications_icon' OR"
    + " name == 'navigation_notifications_icon'")
  private WebElement btnNotifications;

  @iOSXCUITFindAll({
    @iOSXCUITBy(id = "navigation_staff_profile_iphone"),
    @iOSXCUITBy(id = "button_Titlenavigation_staff_profile"),
  })
  private WebElement btnProfile;

  protected NavigationHeaderComponent(WebElement element) {
    super(element);
  }

  /**
   * Click on notifications button
   */
  public void clickOnNotificationsIcon() {
    clickOnCoordinates(getBtnNotifications(), "Notifications icon");
  }

  /**
   * Click on emergency incident button
   */
  public void clickOnEmergencyIncidentIcon() {
    waitForElementToBeClickable(getBtnEmergency(), 5);
    click(getBtnEmergency(), "Emergency incidents icon");
  }

  /**
   * Click on User My Profile
   */
  public void clickOnUserMyProfile() {
    click(getBtnProfile(), "User My Profile");
  }
}