package com.automation.lac.qa.fanapp.mobile.screens.phonenumbervalidation;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class PhoneNumberValidationScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "TBD")
  @AndroidFindBy(xpath = "//*[@resource-id='bgContainer']")
  private WebElement lblOtpNotification;

  @iOSXCUITFindBy(iOSNsPredicate = "name == \"message\" AND label == \", \"")
  @AndroidFindBy(uiAutomator = "descriptionStartsWith(\"Code input\")")
  private WebElement txtPhoneVerificationCode;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'CONFIRM' AND type CONTAINS 'Button'")
  @AndroidFindBy(uiAutomator = "resourceId(\"btnCONFIRM\")")
  private WebElement btnConfirmPhoneNumber;

  @iOSXCUITFindBy(accessibility = "EDIT PHONE NUMBER")
  @AndroidFindBy(uiAutomator = "resourceId(\"tvSecondaryCta\")")
  private WebElement btnEditPhoneNumber;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'hint' AND label CONTAINS 'not correct'")
  @AndroidFindBy(uiAutomator = "textContains(\"the code you entered is not correct\")")
  private WebElement msgWrongOtpCode;
}