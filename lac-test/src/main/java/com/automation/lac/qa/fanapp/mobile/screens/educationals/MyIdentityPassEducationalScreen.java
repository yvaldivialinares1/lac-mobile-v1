package com.automation.lac.qa.fanapp.mobile.screens.educationals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyIdentityPassEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"IdentityPass,\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_image' AND label CONTAINS 'identity pass'")
  private WebElement imgAddIdentityPassLogo;

  @AndroidFindBy(uiAutomator = "resourceId(\"IdentityPassEducationalScreentvTitle\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_label_title' AND label CONTAINS 'identity pass'")
  private WebElement lblAddIdentityPassTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"IdentityPassEducationalScreenbtnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'ADD IDENTITY PASS' AND type CONTAINS 'Button'")
  private WebElement btnAddIdentityPass;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND  type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(uiAutomator = "text(\"Skip\")")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@label, 'Skip, button')]")
  private WebElement btnSkip;
}