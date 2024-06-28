package com.automation.lac.qa.fanapp.mobile.screens.commons.components;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.EXPECTED_STATE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SEARCH;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class SelectStateWidget extends Widget {

  public SelectStateWidget(WebElement element) {
    super(element);
  }

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc, 'Search')]/..")
  @iOSXCUITFindBy(accessibility = "Search state")
  private WebElement inputSearchState;

  /**
   * Return xpath element for state to select
   *
   * @param state to select
   * @return WebElement
   */
  private WebElement btnExpectedState(String state) {
    String androidXpath = "//android.view.View[@content-desc='%s']";
    String iosXpath = "//XCUIElementTypeOther[contains(@label, '%s')]";
    String myXpath = isAndroid() ? androidXpath : iosXpath;
    return getElement(By.xpath(String.format(myXpath, state)));
  }

  public void selectState(String state) {
    sendKeys(getInputSearchState(), state, SEARCH.getValue());
    click(btnExpectedState(state), EXPECTED_STATE.getValue());
  }
}
