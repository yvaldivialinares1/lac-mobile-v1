package com.automation.lac.qa.fanapp.mobile.screens.tickets.component;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class SuggestedSeatComponent extends Widget {

  @AndroidFindBy(xpath = "./*[@resource-id='seatSelectionSeatInfoLabel']")
  @iOSXCUITFindBy(xpath = "./XCUIElementTypeStaticText[1]")
  private WebElement sectionSeatTitle;

  @AndroidFindBy(xpath = ".//*[@resource-id='seatSelectionSeatStdSeatLabel']]")
  private WebElement sectionSeatLabel;

  @AndroidFindBy(xpath = ".//*[@resource-id='seatSelectionSeatPlace']")
  @iOSXCUITFindBy(xpath = "./XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
  private WebElement sectionSeatPlace;

  @AndroidFindBy(xpath = ".//*[@resource-id='seatSelectionTicketInfoText']")
  private WebElement sectionSeatCount;

  @AndroidFindBy(xpath = ".//*[@resource-id='seatSelectionTicketInfoAmount']")
  @iOSXCUITFindBy(xpath = "./XCUIElementTypeStaticText[2]")
  private WebElement sectionSeatPrice;

  protected SuggestedSeatComponent(WebElement element) {
    super(element);
  }
}