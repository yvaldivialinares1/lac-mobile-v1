package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.enums.FanProfileTabOption;
import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class FanProfileTabOptionsComponent extends Widget {

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name = 'fan_tab_item_']")
  private List<WebElement> lstFanTabOptions;

  protected FanProfileTabOptionsComponent(WebElement element) {
    super(element);
  }

  /**
   * Click on fan tab option
   *
   * @param fanProfileTabOption option to click the tab
   */
  @Step("Tap on fan profile tab option {fanProfileTabOption}")
  public void clickOnTabOption(FanProfileTabOption fanProfileTabOption) {
    getLstFanTabOptions().stream()
      .filter(
        option -> containsIgnoreCase(option.getAttribute("label"), fanProfileTabOption.getValue()))
      .findFirst().orElseThrow(() -> new CustomException("Option not found")).click();
  }
}
