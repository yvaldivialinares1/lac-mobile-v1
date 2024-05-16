package com.automation.lac.qa.fanapp.mobile.screens.educationals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AddPaymentMethodEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Payment,\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_image' AND label BEGINSWITH 'add payment'")
  private WebElement imgAddPaymentMethodLogo;

  @AndroidFindBy(uiAutomator = "resourceId(\"PaymentEducationalScreentvTitle\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_label_title' AND label BEGINSWITH 'add payment'")
  private WebElement lblAddPaymentMethodTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"PaymentEducationalScreenbtnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'ADD PAYMENT METHOD' AND type CONTAINS 'Button'")
  private WebElement btnAddPaymentMethod;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND  type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(uiAutomator = "text(\"Skip\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_navigation_option_skip_with_text'")
  private WebElement btnSkip;
}