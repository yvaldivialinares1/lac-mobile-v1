package com.automation.lac.qa.fanapp.mobile.screens.home;

import com.automation.lac.qa.fanapp.mobile.screens.home.components.MenuWidget;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class HomeScreen extends MobileBaseScreen {

  @AndroidFindBy(accessibility = "Theme Switcher Dropdown")
  @iOSXCUITFindBy(accessibility = "theme_switcher_image")
  private WebElement btnThemeSwitch;

  @AndroidFindBy(uiAutomator = "resourceId(\"viewThemeSwitcherItemClippers\")")
  @iOSXCUITFindBy(accessibility = "theme_switcher_as_clippers_fan_row")
  private WebElement btnSwitchToClippersFan;

  @AndroidFindBy(uiAutomator = "resourceId(\"viewThemeSwitcherItemConcert\")")
  @iOSXCUITFindBy(accessibility = "theme_switcher_as_concert_fan_row")
  private WebElement btnSwitchToConcertFan;

  @AndroidFindBy(accessibility = "Message Center")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_navigation_option_bellicon'")
  private WebElement btnNotification;

  @AndroidFindBy(uiAutomator = "resourceId(\"ivProfile\")")
  @iOSXCUITFindBy(accessibility = "id_btn_navigation_option_profileicon")
  private WebElement btnProfile;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Close Menu Button']")
  @iOSXCUITFindBy(accessibility = "navigation_menu_opened")
  private WebElement btnCloseMenu;

  @AndroidFindBy(accessibility = "Menu Button")
  @iOSXCUITFindBy(accessibility = "navigation_menu_closed")
  private WebElement btnMenu;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Close Menu Button']/..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='navigation_menu_opened']/..")
  private MenuWidget menuWidget;
}