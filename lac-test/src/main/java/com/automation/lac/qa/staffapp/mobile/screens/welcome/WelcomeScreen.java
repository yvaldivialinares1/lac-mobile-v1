package com.automation.lac.qa.staffapp.mobile.screens.welcome;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class WelcomeScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "sign_in_brush_text")
  private WebElement lblStaffApp;

  @iOSXCUITFindBy(id = "sign_in_message")
  private WebElement staffAppMessage;

  @iOSXCUITFindBy(id = "sign_in_button")
  private WebElement btnLogIn;

  @iOSXCUITFindBy(id = "sign_in_contact_admin_link")
  private WebElement lnkContactIt;

  @iOSXCUITFindBy(id = "sign_in_contact_admin_link")
  private WebElement lnkPrivacyPolicy;
}