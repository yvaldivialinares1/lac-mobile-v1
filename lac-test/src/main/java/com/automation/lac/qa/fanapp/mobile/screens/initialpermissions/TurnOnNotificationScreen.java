package com.automation.lac.qa.fanapp.mobile.screens.initialpermissions;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.AllowNotificationsComponent;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.EnableLocationComponent;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TurnOnNotificationScreen extends MobileBaseScreen {
  private final AllowNotificationsComponent allowNotificationsComponent;
  private final EnableLocationComponent enableLocationComponent;

  public TurnOnNotificationScreen() {
    this.allowNotificationsComponent = new AllowNotificationsComponent();
    this.enableLocationComponent = new EnableLocationComponent();
  }

  @AndroidFindBy(accessibility = "CONTINUE")
  @iOSXCUITFindBy(id = "app_permissions_view_cta_right_arrow")
  private WebElement btnContinue;
}