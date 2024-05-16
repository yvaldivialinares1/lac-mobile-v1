package com.automation.lac.qa.staffapp.mobile.screens.login;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class LoginScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'Skype')]")
  private WebElement frmInputEmail;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[contains(@name,'password')]")
  private WebElement frmInputPassword;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Sign in with'")
  private WebElement btnPreselectedAccountRow;

  @iOSXCUITFindBy(accessibility = "Continue")
  private WebElement btnContinue;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Next')]")
  private WebElement btnSignInNext;

  @iOSXCUITFindBy(accessibility = "Sign in")
  private WebElement btnSignIn;
}