package com.automation.lac.qa.staffapp.mobile.screens.commons.components;

import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeUnavailable;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class ProgressBarComponent extends Widget {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'commonProgressView_loader'")
  private WebElement progressBarIcon;

  protected ProgressBarComponent(WebElement element) {
    super(element);
  }

  /**
   * wait while spinner indicator is displayed
   */
  public void waitForSpinnerDisappear() {
    waitElementWillBeAvailable(progressBarIcon, 5);
    waitElementWillBeUnavailable(progressBarIcon, 60);
  }
}
