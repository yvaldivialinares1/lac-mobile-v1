package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class GameFaceIdAddedSuccessScreen extends MobileBaseScreen {
  @AndroidFindBy(id = "Game Face addedTitle")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement lblTitle;

  @AndroidFindBy(id = "Game Face addedSubtitle")
  @iOSXCUITFindBy(id = "TBD")
  private WebElement lblSubTitle;

}