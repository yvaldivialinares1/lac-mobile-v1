package com.automation.lac.qa.fanapp.mobile.screens.tickets;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class SeatsSelectionScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//*[@resource-id='ivBannerGameDetail']")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement eventTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvHowManyFanQuestion']")
  @iOSXCUITFindBy(accessibility = "select_seat_count_total_seat_title")
  private WebElement howManyFanQuestionTitle;

  @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='ivStepperIncrease'])[1]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Increase seats by 1'][1]")
  private WebElement increaseSeatsByOneTitle;

  @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='ivStepperDecrease'])[1]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='select_seat_count_total_stepper_view'][1]"
    + "//XCUIElementTypeButton[@label='Decrease seats by 1']")
  private WebElement decreaseSeatsByOneTitle;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Kids under 2 years don’t need seats.']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='select_seat_count_chip_index_2']")
  private WebElement kidsDoNotNeedSeatTitle;

  @AndroidFindBy(xpath = "(//android.view.View[@resource-id='tvStepperValidation'])[1]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='select_seat_count_chip_index_0'][1]")
  private WebElement limitReachedTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='btFindTickets']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='game_details_cta_title_label']")
  private WebElement findSeatButton;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvQuestionWheelchair']"
    + "/following-sibling::android.view.View")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='select_seat_count_toggle_button']"
    + "//XCUIElementTypeStaticText")
  private WebElement accessibleSeatButton;

  @AndroidFindBy(xpath = "//*[@resource-id='tvSelectedSeatCount']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText"
          + "[@name='select_seat_count_total_stepper_view']")
  private WebElement selectedSeatCountFromScreen;
}