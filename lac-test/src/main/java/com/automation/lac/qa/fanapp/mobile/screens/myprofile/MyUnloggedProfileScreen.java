package com.automation.lac.qa.fanapp.mobile.screens.myprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyUnloggedProfileScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "text(\"My Profile\")")
  @iOSXCUITFindBy(accessibility = "home_my_profile_title")
  WebElement lblMyProfileTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"tvCta\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'LOG' AND type == 'XCUIElementTypeButton'")
  WebElement btnLoginCreateAccount;

  @AndroidFindBy(uiAutomator = "description(\"containerMyProfileApp Settings\")")
  @iOSXCUITFindBy(accessibility = "id_btn_home_my_profile_app_settings_title")
  WebElement btnAppSettings;

  @AndroidFindBy(uiAutomator = "description(\"containerMyProfileTerms & Conditions\")")
  @iOSXCUITFindBy(accessibility = "id_btn_home_my_profile_term_and_condition_title")
  WebElement btnTermsAndConditions;

  @AndroidFindBy(uiAutomator = "description(\"containerMyProfilePrivacy Policy\")")
  @iOSXCUITFindBy(accessibility = "id_btn_home_my_profile_privacy_policy_title")
  WebElement btnPrivacyPolicy;

  @AndroidFindBy(accessibility = "Back")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  WebElement btnBack;
}