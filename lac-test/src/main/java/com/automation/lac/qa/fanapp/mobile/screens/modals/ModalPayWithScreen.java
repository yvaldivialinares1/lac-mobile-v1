package com.automation.lac.qa.fanapp.mobile.screens.modals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ModalPayWithScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"tvModalTitle\").textContains(\"Pay with\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'payment_method_add_card_title'"
    + " AND label == 'Pay with'")
  private WebElement txtPayWith;

  @AndroidFindBy(uiAutomator = "textContains(\"ADD CARD\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'ADD CARD'")
  private WebElement btnPayWithAddCard;

  @AndroidFindBy(uiAutomator = "textContains(\"CONTINUE\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'CONTINUE'")
  private WebElement btnPayWithContinue;

  @AndroidFindBy(uiAutomator = "resourceId(\"tvSecondaryCta\").textContains(\"CANCEL\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_secondary_button'")
  private WebElement btnModalSecondary;

}


