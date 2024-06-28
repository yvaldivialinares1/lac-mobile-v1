package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.enums.ManualAgeVerificationOption;
import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class ManualAgeVerificationComponent extends Widget {

  @iOSXCUITFindBy(id = "CLOSE")
  private WebElement btnClose;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'radio_circle'")
  private List<WebElement> verificationOptionsList;

  @iOSXCUITFindBy(id = "verify_button")
  private WebElement btnVerify;

  protected ManualAgeVerificationComponent(WebElement element) {
    super(element);
  }

  /**
   * select manual age verification option.
   */
  public void selectManualAgeVerificationOption(ManualAgeVerificationOption option) {
    WebElement webElement = verificationOptionsList.stream()
      .filter(element -> element.getAttribute("label").equals(option.getLabel()))
      .findFirst()
      .orElseThrow(
        () -> new CustomException("No option is found with the label " + option.getLabel()));
    click(webElement, option.getLabel());
  }
}
