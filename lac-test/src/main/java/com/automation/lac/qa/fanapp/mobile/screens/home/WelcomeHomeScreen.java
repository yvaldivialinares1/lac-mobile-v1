package com.automation.lac.qa.fanapp.mobile.screens.home;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class WelcomeHomeScreen extends MobileBaseScreen {

  @AndroidFindBy(accessibility = "Create an account")
  @iOSXCUITFindBy(accessibility = "primary_rectangle_button")
  private WebElement btnCreateAnAccount;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Skip']/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_navigation_option_skip_with_text'")
  private WebElement btnSkip;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Google button\")")
  @iOSXCUITFindBy(accessibility = "id_google_button")
  private WebElement btnGoogleSignUp;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Apple button\")")
  @iOSXCUITFindBy(accessibility = "id_apple_button")
  private WebElement btnAppleSignUp;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"NBA button\")")
  @iOSXCUITFindBy(accessibility = "id_nba_button")
  private WebElement btnNbaSignUp;

  @AndroidFindBy(uiAutomator = "textContains(\"LOG IN\")")
  @iOSXCUITFindBy(accessibility = "LOG IN")
  private WebElement btnLogIn;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Intuit Dome Logo\")")
  @iOSXCUITFindBy(accessibility = "id_intuit_dome")
  private WebElement imgIntuitDomeLogo;
}