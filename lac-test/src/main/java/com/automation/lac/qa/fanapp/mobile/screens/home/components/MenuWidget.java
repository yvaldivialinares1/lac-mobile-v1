package com.automation.lac.qa.fanapp.mobile.screens.home.components;

import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import io.appium.java_client.pagefactory.Widget;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class MenuWidget extends Widget {

  public MenuWidget(WebElement element) {
    super(element);
  }

  private static final String ANDROID_BTN = "//android.view.View[contains(@content-desc, '%s')]";
  private static final String IOS_BTN = "//XCUIElementTypeButton[contains(@label, '%s')]";

  /**
   * @param menuItem menu item to be fetched
   * @return WebElement of the specified menu item
   */
  public WebElement getMenuButton(String menuItem) {
    String buttonXpath = isAndroid() ? ANDROID_BTN : IOS_BTN;
    return getElement(By.xpath(String.format(buttonXpath, menuItem.toUpperCase())));
  }
}