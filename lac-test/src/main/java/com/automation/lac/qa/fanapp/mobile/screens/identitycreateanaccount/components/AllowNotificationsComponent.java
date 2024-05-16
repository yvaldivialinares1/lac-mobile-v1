package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AllowNotificationsComponent {

  @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
  @iOSXCUITFindBy(accessibility = "Allow")
  private WebElement btnAllow;

  @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
  @iOSXCUITFindBy(accessibility = "Don’t Allow")
  private WebElement btnDeny;
}