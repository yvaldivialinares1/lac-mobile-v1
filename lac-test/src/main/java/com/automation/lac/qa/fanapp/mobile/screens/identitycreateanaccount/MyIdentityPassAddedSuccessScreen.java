package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyIdentityPassAddedSuccessScreen extends MobileBaseScreen {
  @AndroidFindBy(id = "Identity pass addedTitle")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement lblTitle;

  @AndroidFindBy(id = "Identity pass addedSubtitle")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement lblSubTitle;

}