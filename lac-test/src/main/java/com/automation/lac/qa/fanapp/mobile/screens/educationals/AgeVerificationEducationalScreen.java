package com.automation.lac.qa.fanapp.mobile.screens.educationals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AgeVerificationEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"AgeVerification,\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_image' AND label BEGINSWITH 'verify your age'")
  private WebElement imgAgeVerificationLogo;

  @AndroidFindBy(uiAutomator = "resourceId(\"AgeVerificationEducationalScreentvTitle\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'title' AND label BEGINSWITH 'verify your age'")
  private WebElement lblVerifyYourAgeTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"AgeVerificationEducationalScreenbtnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'VERIFY MY AGE' AND type == 'XCUIElementTypeButton'")
  private WebElement btnVerifyMyAge;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND  type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(uiAutomator = "text(\"Skip\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_navigation_option_skip_with_text'")
  private WebElement btnSkip;
}