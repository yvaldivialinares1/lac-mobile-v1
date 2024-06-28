package com.automation.lac.qa.staffapp.mobile.screens.payment;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class PaymentsAndSharedPaymentsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "payment_sections_back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "payment_sections_header_title")
  private WebElement lblTitle;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Payments_methods'")
  private List<WebElement> lstPaymentButtons;
}
