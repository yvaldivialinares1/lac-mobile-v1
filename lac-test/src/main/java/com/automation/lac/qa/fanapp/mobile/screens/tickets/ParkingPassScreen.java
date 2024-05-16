package com.automation.lac.qa.fanapp.mobile.screens.tickets;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ParkingPassScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Choose your parking preference']")
  @AndroidFindBy(xpath = "//*[@resource-id='tvSelectGarageHeader']")
  private WebElement titleAvlGarageParking;

  @AndroidFindBy(xpath = "//*[@resource-id='tvActionTextToolbar']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
  private WebElement btnCancel;

  @iOSXCUITFindBy(accessibility = "garage_selection_preference_STND_sold_out_label")
  @AndroidFindBy(xpath = "//android.view.View[@resource-id="
          + "'boxSelectGarageStandard_STND_SOLD_OUT']//android.view.View[position()=1]")
  private WebElement lblParkingStdSoldOut;

  @iOSXCUITFindBy(xpath =
          "(//XCUIElementTypeStaticText[@name='garage_selection_cta_title_label'])[position()=2]")
  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvContinue']")
  private WebElement btnParkingContinue;

  @AndroidFindBy(xpath = "//*[@resource-id='tvInfo']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name="
          + "'garage_selection_maximum_allowed_parking_pass_label']")
  private WebElement lblMaxParkingPass;

  @AndroidFindBy(xpath = "//*[@resource-id='tvMultiGarageOptionLabel']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name="
          + "'garage_selection_which_garage_preference_title']")
  private WebElement lblPageHeadline;

  @AndroidFindBy(xpath = "//*[@resource-id='tvSingleGarageOption']")
  @iOSXCUITFindBy(accessibility = "garage_selection_which_garage_preference_title")
  private WebElement titleSingleGarageOption;
}
