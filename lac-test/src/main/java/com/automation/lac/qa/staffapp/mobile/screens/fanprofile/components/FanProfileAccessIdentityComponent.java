package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class FanProfileAccessIdentityComponent extends Widget {

  @iOSXCUITFindBy(id = "back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "fan_identity_game_face_title")
  private WebElement lblGameFaceIdTitle;

  @iOSXCUITFindBy(id = "ADD")
  private WebElement btnAddGameFace;

  @iOSXCUITFindBy(id = "fan_identity_game_face_remove_button")
  private WebElement btnRemoveGameFace;

  @iOSXCUITFindBy(id = "Age verification header")
  private WebElement lblAgeVerificationTitle;

  @iOSXCUITFindBy(id = "fan_identity_age_verification_verify_manually_button")
  private WebElement btnVerifyManually;

  @iOSXCUITFindBy(id = "fan_age_verification_remove_button")
  private WebElement btnRemoveAgeVerification;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_identity_identity_pass_title'"
    + " or name == 'Fan identity header'")
  private WebElement lblIdentityPassTitle;

  @iOSXCUITFindBy(id = "age_verification_state")
  private WebElement lblAgeVerificationState;

  @iOSXCUITFindBy(id = "manually_age_verification_source")
  private WebElement lblAgeVerificationSource;

  protected FanProfileAccessIdentityComponent(WebElement element) {
    super(element);
  }
}
