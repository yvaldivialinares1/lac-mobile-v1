package com.automation.lac.qa.fanapp.mobile.screens.paymentmethods;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyPaymentsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "Payment Methods Management")
  @AndroidFindBy(xpath = "//android.view.View[contains("
    + "@content-desc,'Payment Methods Management')]")
  private WebElement btnPaymentMethodManagement;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Payments History')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Payments History']")
  private WebElement btnPaymentsHistory;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Share your Payment Methods\"]")
  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Share your "
    + "Payment Methods')]")
  private WebElement btnSharePayments;
}