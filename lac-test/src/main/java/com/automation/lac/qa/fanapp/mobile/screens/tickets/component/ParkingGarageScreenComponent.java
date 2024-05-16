package com.automation.lac.qa.fanapp.mobile.screens.tickets.component;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ParkingGarageScreenComponent extends Widget {

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeStaticText[@name='garage_selection_preference_ADA_name_label']")
  @AndroidFindBy(uiAutomator = "resourceId(\"tvSelectGarageTitleADA\")")
  private WebElement titleAccParking;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeStaticText[@name='garage_selection_preference_ELEC_name_label']")
  @AndroidFindBy(uiAutomator = "resourceId(\"tvSelectGarageTitleELEC\")")
  private WebElement titleEleParking;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeStaticText[@name='garage_selection_preference_STND_name_label']")
  @AndroidFindBy(uiAutomator = "resourceId(\"tvSelectGarageTitleSTND\")")
  private WebElement titleStdParking;

  @iOSXCUITFindBy(accessibility = "garage_selection_preference_STND_count_label")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"tvSelectGarageCountSTND\")")
  private WebElement lblGarageCountStd;

  @iOSXCUITFindBy(accessibility = "garage_selection_preference_ELEC_count_label")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"tvSelectGarageCountELEC\")")
  private WebElement lblGarageCountEle;

  @iOSXCUITFindBy(accessibility = "garage_selection_preference_ADA_count_label")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"tvSelectGarageCountADA\")")
  private WebElement lblGarageCountAda;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='garage_selection_preference_ADA_plus_button_disabled']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"ivSelectGarageIncrementImageADA\")")
  private WebElement btnIncreaseAccParkingDisabled;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='garage_selection_preference_ELEC_plus_button_disabled']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"ivSelectGarageIncrementImageELEC\")")
  private WebElement btnIncreaseEleParkingDisabled;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='garage_selection_preference_STND_plus_button_disabled']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"ivSelectGarageIncrementImageSTND\")")
  private WebElement btnIncreaseStdParkingDisabled;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeOther[@name='Accessible Parking SOLD OUT']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"boxSelectGarageElectric_ADA_SOLD_OUT\")")
  private WebElement lblSoldOutAccParking;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeOther[@name='Electric Parking SOLD OUT']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"boxSelectGarageElectric_ELEC_SOLD_OUT\")")
  private WebElement lblSoldOutEleParking;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeOther[@name='Standard Parking SOLD OUT']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"boxSelectGarageElectric_STND_SOLD_OUT\")")
  private WebElement lblSoldOutStdParking;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='garage_selection_preference_STND_minus_button_enabled']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"ivSelectGarageDecrementImageSTND\")")
  private WebElement btnDecrementImageStand;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='garage_selection_preference_ELEC_minus_button_enabled']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"ivSelectGarageDecrementImageELEC\")")
  private WebElement btnDecrementImageEle;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='garage_selection_preference_ADA_minus_button_enabled']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"ivSelectGarageDecrementImageADA\")")
  private WebElement btnDecrementImageAda;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name="
    + "'garage_selection_available_garage_cta_1']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"tvGarageOption0\")")
  private WebElement firstGarageOption;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name="
    + "'garage_selection_available_garage_cta_2']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"tvGarageOption1\")")
  private WebElement secondGarageOption;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name="
    + "'garage_selection_available_garage_cta_3']")
  @AndroidFindBy(uiAutomator =
    "resourceId(\"tvGarageOption2\")")
  private WebElement thirdGarageOption;

  protected ParkingGarageScreenComponent(WebElement element) {
    super(element);
  }
}
