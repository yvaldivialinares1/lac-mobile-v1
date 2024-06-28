package com.automation.lac.qa.fanapp.mobile.screens.commons;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CommonsScreen extends MobileBaseScreen {
  @AndroidFindBy(uiAutomator = "descriptionContains(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Back\" AND type == \"XCUIElementTypeButton\"")
  private WebElement btnBack;

  @AndroidFindBy(xpath = "//android.view.View[android.view.View[@content-desc='Back']]"
    + "/following-sibling::*")
  @iOSXCUITFindBy(id = "navigation_bar_title_label")
  private WebElement title;

  @AndroidFindBy(accessibility = "Menu Button")
  @iOSXCUITFindBy(accessibility = "navigation_menu_closed")
  private WebElement btnMenu;

  protected static final String XPATH_EXPECTED_OPTION = "//android.view.View[android.view.View"
    + "[@content-desc='Back']]/..//*[contains(@text,'%s') "
    + "or contains(@content-desc,'%s')]";

  public String getAndroidTitle(String expectedOption) {
    return String.format(XPATH_EXPECTED_OPTION, expectedOption, expectedOption);
  }
}