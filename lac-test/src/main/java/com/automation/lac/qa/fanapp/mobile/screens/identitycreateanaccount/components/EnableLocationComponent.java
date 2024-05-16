package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class EnableLocationComponent {

  @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
  @iOSXCUITFindBy(accessibility = "Allow While Using App")
  private WebElement btnWhileUsingTheApp;

  @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
  @iOSXCUITFindBy(accessibility = "Allow Once")
  private WebElement btnOnlyThisTime;

  @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
  @iOSXCUITFindBy(accessibility = "Don’t Allow")
  private WebElement btnDeny;
}