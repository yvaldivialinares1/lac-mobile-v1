package com.automation.lac.qa.staffapp.mobile.screens.home.components;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class FanBarComponent extends Widget {

  @iOSXCUITFindBy(id = "search_fan_button")
  private WebElement btnSearchFan;

  @iOSXCUITFindBy(id = "qr_code_search_button_home")
  private WebElement btnQrSearchFan;

  @iOSXCUITFindBy(id = "biometrics_search_button_home")
  private WebElement btnBiometricsSearchFan;

  @iOSXCUITFindBy(id = "add_user_button")
  private WebElement btnCreateAFan;

  protected FanBarComponent(WebElement element) {
    super(element);
  }

  /**
   * Click on search fan button
   */
  public void clickOnSearchFanButton() {
    waitForElementToBeClickable(getBtnSearchFan(), 5);
    click(getBtnSearchFan(), "Search fan button");
  }
}
