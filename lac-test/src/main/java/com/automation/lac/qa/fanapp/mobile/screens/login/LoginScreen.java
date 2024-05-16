package com.automation.lac.qa.fanapp.mobile.screens.login;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class LoginScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "className(\"android.widget.EditText\").instance(0)")
  @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
  private WebElement inputEmailAddress;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfPASSWORD*\")")
  @iOSXCUITFindBy(className = "XCUIElementTypeSecureTextField")
  private WebElement inputPassword;

  @AndroidFindBy(accessibility = "Forgot your password?")
  @iOSXCUITFindBy(accessibility = "id_btn_forgot_password")
  private WebElement btnForgotYourPassword;

  @AndroidFindBy(uiAutomator = "resourceId(\"btnLOG IN\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'LOG IN' AND type == 'XCUIElementTypeButton'")
  private WebElement btnSignIn;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(uiAutomator = "description(\"LOG IN WITH ANOTHER USER\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'LOG IN WITH ANOTHER USER'")
  private WebElement btnLogInWithAnotherAccount;
}