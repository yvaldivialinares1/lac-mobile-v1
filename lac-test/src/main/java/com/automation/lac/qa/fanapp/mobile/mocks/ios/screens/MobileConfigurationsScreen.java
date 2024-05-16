package com.automation.lac.qa.fanapp.mobile.mocks.ios.screens;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MobileConfigurationsScreen {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'CONTINUE' AND type == 'XCUIElementTypeButton'")
  private WebElement btnContinue;

  @iOSXCUITFindBy
    (iOSNsPredicate = "name == 'MOCK GEOLOCATION' AND type == 'XCUIElementTypeSwitch'")
  private WebElement swtchMockGeolocation;

  @iOSXCUITFindBy(iOSNsPredicate = "name == '20 years old' AND type == 'XCUIElementTypeSwitch'")
  private WebElement swtch20YearsOld;

  @iOSXCUITFindBy(iOSNsPredicate = "name == '45 years old' AND type == 'XCUIElementTypeSwitch'")
  private WebElement swtch45YearsOld;

  @iOSXCUITFindBy
    (iOSNsPredicate = "name == 'At create account flow' AND type == 'XCUIElementTypeSwitch'")
  private WebElement swtchAtCreateAccountFlow;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Done' AND type == 'XCUIElementTypeButton'")
  private WebElement btnDone;

}