package com.automation.lac.qa.fanapp.mobile.screens.gamefaceid;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class VerifyYourAgeScreen extends MobileBaseScreen {

  @AndroidFindBy(accessibility = "UnnecessaryAgeVerificationViewtvTitle")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement lblTitle;

  @AndroidFindBy(id = "btnCONTINUE")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement btnContinue;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='VERIFY YOUR AGE']"
    + "/following-sibling::android.view.View[@content-desc")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement lblSubTitle;
}