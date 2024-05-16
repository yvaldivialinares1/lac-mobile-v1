package com.automation.lac.qa.staffapp.mobile.screens.home.components;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class NavigationHeaderScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "button_Titleemergency_contact_icon")
  private WebElement btnEmergency;

  @iOSXCUITFindAll({
    @iOSXCUITBy(id = "manual_search_icon"),
    @iOSXCUITBy(id = "search_Title")
  })
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

  @iOSXCUITFindAll({
    @iOSXCUITBy(id = "navigation_notifications_icon"),
    @iOSXCUITBy(id = "button_Titlenavigation_notifications_icon")
  })
  private WebElement btnNotifications;

  @iOSXCUITFindAll({
    @iOSXCUITBy(id = "navigation_staff_profile"),
    @iOSXCUITBy(id = "button_Titlenavigation_staff_profile"),
  })
  private WebElement btnProfile;
}