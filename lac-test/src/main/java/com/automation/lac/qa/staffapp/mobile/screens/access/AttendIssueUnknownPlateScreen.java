package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AttendIssueUnknownPlateScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "search_icon")
  private WebElement searchInputSearchIcon;

  @iOSXCUITFindBy(accessibility = "search_bar_input")
  private WebElement manualSearchInputField;

  @iOSXCUITFindBy(accessibility = "search_bar_hint_text")
  private WebElement manualSearchBarHintText;

  @iOSXCUITFindBy(accessibility = "clear_button")
  private WebElement clearManualSearchInputButton;

  @iOSXCUITFindBy(accessibility = "qr_code_search_button")
  private WebElement navigationHeaderSearchInputQrCodeSearchButton;

  @iOSXCUITFindBy(accessibility = "biometrics_search_button")
  private WebElement navigationHeaderSearchInputBioMetricSearchButton;

  @iOSXCUITFindBy(accessibility = "SELECT A FAN ACCOUNT TO REGISTER THE VEHICLE")
  private WebElement titleSelectFanAccountToRegisterVehicle;

  @iOSXCUITFindBy(accessibility = "Does the fan not have an account?")
  private WebElement lblFanNotHaveAccount;

  @iOSXCUITFindBy(accessibility = "attend_issue_container_secondary_title")
  private WebElement attendIssueSecondaryTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='user_name']")
  private WebElement attendIssueFanNameList;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='profile_photo']")
  private WebElement attendIssueFanProfilePhoto;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='email_id']")
  private WebElement attendIssueFanEmailList;

  @iOSXCUITFindBy(accessibility = "attend_issue_primary_button")
  private WebElement attendIssuePrimaryButton;
}
