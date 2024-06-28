package com.automation.lac.qa.staffapp.mobile.screens.myuserprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyIdentityScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_identity_identity_pass_wiew_title'"
    + " or name == 'staff_identity_pass_text'")
  private WebElement panelMyIdentityTitle;

  @iOSXCUITFindBy(accessibility = "fan_identity_identity_pass_wiew_close_button")
  private WebElement btnCloseMyIdentityTitle;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'Scan this QR code to add your Identity Pass on your device'")
  private WebElement lblQrInfoMessage;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'QR Code'")
  private WebElement imgQrCode;

  @iOSXCUITFindBy(xpath =
    "(//XCUIElementTypeStaticText[@name='first_last_name_label'])[position()=2]")
  private WebElement lblFirstAndLastName;

  @iOSXCUITFindBy(xpath =
    "(//XCUIElementTypeStaticText[@name='profile_name_initials'])[position()=2]")
  private WebElement lblProfileNameInitials;

  @iOSXCUITFindBy(xpath =
    "(//XCUIElementTypeImage[@name='navigation_staff_profile'])[position()=3]")
  private WebElement imgStaffProfile;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'member_area_label' AND label == 'GUEST SUPPORT'")
  private WebElement lblMemberArea;

  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='role_label'])[position()=2]")
  private WebElement lblRole;
}
