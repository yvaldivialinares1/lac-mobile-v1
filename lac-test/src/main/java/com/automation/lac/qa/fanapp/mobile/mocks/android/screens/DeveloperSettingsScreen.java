package com.automation.lac.qa.fanapp.mobile.mocks.android.screens;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class DeveloperSettingsScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "text(\"Dev Settings\")")
  private WebElement lblTitle;

  @AndroidFindBy(uiAutomator = "className(\"android.view.View\").checkable(true).instance(0)")
  private WebElement switchSomeOtherFlag;

  @AndroidFindBy(uiAutomator = "className(\"android.view.View\").checkable(true).instance(1)")
  private WebElement switchGameFaceSkipCamera;

  @AndroidFindBy(uiAutomator = "className(\"android.view.View\").checkable(true).instance(2)")
  private WebElement switchIdentityPassSkip;

  @AndroidFindBy(uiAutomator = "className(\"android.view.View\").checkable(true).instance(3)")
  private WebElement switchPhoneOtpSkip;

}