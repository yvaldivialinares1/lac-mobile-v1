package com.automation.lac.qa.fanapp.mobile.screens.tickets.component;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class SuggestedSeatComponent extends Widget {

  @AndroidFindBy(uiAutomator = "resourceId(\"seatSelectionSeatInfoLabel\")")
  @iOSXCUITFindBy(xpath = "./XCUIElementTypeStaticText[1]")
  private WebElement sectionSeatTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='seatSelectionSeatInfoInfo']/android.view.View[1]")
  @iOSXCUITFindBy(xpath = "./XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
  private WebElement sectionSeatInfo;

  @AndroidFindBy(uiAutomator = "resourceId(\"seatSelectionTicketInfoText\")")
  private WebElement sectionSeatCount;

  @AndroidFindBy(uiAutomator = "resourceId(\"seatSelectionTicketInfoAmount\")")
  @iOSXCUITFindBy(xpath = "./XCUIElementTypeStaticText[2]")
  private WebElement sectionSeatPrice;

  protected SuggestedSeatComponent(WebElement element) {
    super(element);
  }
}