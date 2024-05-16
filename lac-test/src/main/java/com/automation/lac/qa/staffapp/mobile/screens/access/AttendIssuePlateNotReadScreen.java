package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AttendIssuePlateNotReadScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "attend_issue_verify_license_plate_correct")
  private WebElement attendIssueVerifyLicensePlateCorrectSubTitle;

  @iOSXCUITFindBy(accessibility = "alert_cancel_button")
  private WebElement cancelLogoutButton;

  @iOSXCUITFindBy(accessibility = "vehicle_licensePlate_inputfield")
  private WebElement vehicleLicensePlateInputField;

  @iOSXCUITFindBy(accessibility = "vehicle_state_inputfield")
  private WebElement vehicleStateInputField;
}
