package com.automation.lac.qa.fanapp.mobile.screens.myprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyAccountSettingsScreen extends MobileBaseScreen {
  @AndroidFindBy(uiAutomator = "descriptionContains(\"Personal Information\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_personalinformation'")
  private WebElement btnPersonalInformation;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Password & Security\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_passwordsecurity'")
  private WebElement btnPasswordAndSecurity;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"My Identity\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_myidentity'")
  private WebElement btnMyIdentity;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Teammate Accounts\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_teammate_accounts'")
  private WebElement btnTeammateAccounts;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"QR Code\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_qrcode'")
  private WebElement btnQrCode;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Delete My Account\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_delete_my_account'")
  private WebElement btnDeleteMyAccount;

  @AndroidFindBy(accessibility = "Log Out   Button, Double tap to activate")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_home_my_profile_logout_title'")
  private WebElement btnLogOut;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  private WebElement btnBack;
}
