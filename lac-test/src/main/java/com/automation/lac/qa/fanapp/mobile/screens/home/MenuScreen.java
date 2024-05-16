package com.automation.lac.qa.fanapp.mobile.screens.home;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MenuScreen extends MobileBaseScreen {

  @AndroidFindBy(accessibility = "Close Menu Button")
  @iOSXCUITFindBy(iOSNsPredicate = "navigation_menu_opened")
  private WebElement btnClose;

  @AndroidFindBy(accessibility = "HOMEButton")
  @iOSXCUITFindBy(accessibility = "navigation_menu_option_0")
  private WebElement btnHome;

  @AndroidFindBy(accessibility = "TEAMButton")
  @iOSXCUITFindBy(accessibility = "navigation_menu_option_1")
  private WebElement btnTeam;

  @AndroidFindBy(accessibility = "INTUIT DOMEButton")
  @iOSXCUITFindBy(accessibility = "navigation_menu_option_2")
  private WebElement btnIntuitDome;

  @AndroidFindBy(accessibility = "TICKETSButton")
  @iOSXCUITFindBy(accessibility = "navigation_menu_option_3")
  private WebElement btnTickets;

  @AndroidFindBy(accessibility = "SUITESButton")
  @iOSXCUITFindBy(accessibility = "navigation_menu_option_4")
  private WebElement btnSuites;
}