package com.automation.lac.qa.fanapp.mobile.screens.paymentmethods;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class MyPaymentMethodsScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "textContains(\"ADD CARD\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"payment_method_add_card_title\"")
  private WebElement btnAddCard;

  @AndroidFindBy(uiAutomator = "resourceId(\"tvBrandName\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"payment_method_card_name_label\"")
  private List<WebElement> cardsList;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"tvPreferred\"]"
    + "//preceding-sibling::android.widget.TextView[@resource-id = 'tvBrandName']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText"
    + "[@name=\"payment_method_card_preferred_label\"]"
    + "//preceding-sibling::XCUIElementTypeStaticText[@name=\"payment_method_card_name_label\"]")
  private WebElement preferredCard;

  @AndroidFindBy(uiAutomator = "resourceId(\"content\")"
    + ".childSelector(className(\"android.view.View\"))"
    + ".childSelector(className(\"android.view.View\")).instance(0)")
  @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText"
    + "[`name == \"banner_message_text\"`][2]")
  private WebElement txtBannerMessage;

  @AndroidFindBy(uiAutomator = ".resourceId(\"cvHeaderMessage\")"
    + ".descriptionContains(\"Payment Methods\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label == \"Payment Methods\"")
  private WebElement lblPaymentMethods;

  //this shows up only when there are no cards in list..

  @AndroidFindBy(uiAutomator = "textContains(\"ADD PAYMENT METHOD\")")
  @iOSXCUITFindBy(iOSNsPredicate = "value == \"ADD PAYMENT METHOD\"")
  private WebElement btnAddPaymentMethod;

  @AndroidFindBy(uiAutomator = "textContains"
    + "(\"YOU DON'T HAVE ANY PAYMENT METHOD YET\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label == \"YOU DON'T HAVE ANY PAYMENT METHOD YET\"")
  private WebElement lblNoPaymentMethodsYet;

  /**
   * get card Details Card Number
   */
  public static WebElement getCardNumber(String lastFour) {
    if (!isAndroid()) {
      return getDriver().findElement(
        By.xpath(String.format("//XCUIElementTypeStaticText[@name='card_"
          + "details_card_number'][contains(@label,'%s')]", lastFour)));
    } else {
      return getDriver().findElement(
        By.xpath(String.format("//*[@resource-id='tvBrandName'][contains(@text,'%s')]", lastFour)));
    }
  }
}