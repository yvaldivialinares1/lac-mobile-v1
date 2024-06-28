package com.automation.lac.qa.fanapp.mobile.screens.developersettings;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class DeveloperSettingsScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"GEOLOCATION (NoMock)\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggleGeolocation;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Restricted State\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggleRestrictedState;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"GAMEFACE ID (NoMock)\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggleGameFaceId;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"21 year old\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggle21YearsOld;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"45 years old\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggle45YearsOld;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Gameface Skip Camera\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggleGameFaceSkipCamera;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Bypass Identity Pass\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggleBypassIdentityPass;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Bypass phone OTP\"]"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement toggleBypassPhoneOtp;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Developer Settings\"]")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement devSettingsTitle;


}