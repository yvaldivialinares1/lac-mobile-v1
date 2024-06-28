package com.automation.lac.qa.fanapp.mobile.screens.tickets;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ParkingPassScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "Choose your parking preference")
  @AndroidFindBy(id = "tvSelectGarageHeader")
  private WebElement titleAvlGarageParking;

  @iOSXCUITFindBy(iOSNsPredicate = "name = 'Cancel' AND type == 'XCUIElementTypeButton'")
  @AndroidFindBy(id = "tvActionTextToolbar")
  private WebElement btnCancel;

  @iOSXCUITFindBy(id = "garage_selection_maximum_allowed_parking_pass_label")
  @AndroidFindBy(id = "tvInfo")
  private WebElement lblMaxParkingPass;

  @iOSXCUITFindBy(id = "garage_selection_which_garage_preference_title")
  @AndroidFindBy(id = "tvSelectGarageHeader")
  private WebElement lblPageHeadline;

  @iOSXCUITFindBy(accessibility = "garage_selection_which_garage_preference_title")
  @AndroidFindBy(id = "tvSingleGarageOption")
  private WebElement titleSingleGarageOption;

  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeScrollView'")
  @AndroidFindBy(className = "android.widget.ScrollView")
  private WebElement parkingGarageScreenScrollView;

  @iOSXCUITFindBy(id = "garage_selection_preference_ADA_name_label")
  @AndroidFindBy(id = "tvSelectGarageTitleADA")
  private WebElement titleAccParking;

  @iOSXCUITFindBy(id = "garage_selection_preference_ELEC_name_label")
  @AndroidFindBy(id = "tvSelectGarageTitleELEC")
  private WebElement titleEleParking;

  @iOSXCUITFindBy(id = "garage_selection_preference_STND_name_label")
  @AndroidFindBy(id = "tvSelectGarageTitleSTND")
  private WebElement titleStdParking;

  @iOSXCUITFindBy(id = "garage_selection_preference_STND_count_label")
  @AndroidFindBy(id = "tvSelectGarageCountSTND")
  private WebElement lblGarageCountStd;

  @iOSXCUITFindBy(id = "garage_selection_preference_ELEC_count_label")
  @AndroidFindBy(id = "tvSelectGarageCountELEC")
  private WebElement lblGarageCountEle;

  @iOSXCUITFindBy(id = "garage_selection_preference_ADA_count_label")
  @AndroidFindBy(id = "tvSelectGarageCountADA")
  private WebElement lblGarageCountAda;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name BEGINSWITH 'garage_selection_preference_ADA_plus_button_'")
  @AndroidFindBy(id = "ivSelectGarageIncrementImageADA")
  private WebElement btnIncreaseAccParking;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name BEGINSWITH 'garage_selection_preference_ELEC_plus_button_'")
  @AndroidFindBy(id = "ivSelectGarageIncrementImageELEC")
  private WebElement btnIncreaseEleParking;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name BEGINSWITH 'garage_selection_preference_STND_plus_button_'")
  @AndroidFindBy(id = "ivSelectGarageIncrementImageSTND")
  private WebElement btnIncreaseStdParking;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Accessible Parking SOLD OUT'")
  @AndroidFindBy(id = "boxSelectGarageAccessible_ADA_SOLD_OUT")
  private WebElement lblSoldOutAccParking;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Electric Parking SOLD OUT'")
  @AndroidFindBy(id = "boxSelectGarageElectric_ELEC_SOLD_OUT")
  private WebElement lblSoldOutEleParking;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Standard Parking SOLD OUT'")
  @AndroidFindBy(id = "boxSelectGarageStandard_STND_SOLD_OUT")
  private WebElement lblSoldOutStdParking;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name BEGINSWITH 'garage_selection_preference_STND_minus_button_'")
  @AndroidFindBy(id = "ivSelectGarageDecrementImageSTND")
  private WebElement btnDecrementImageStand;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name BEGINSWITH 'garage_selection_preference_ELEC_minus_button_'")
  @AndroidFindBy(id = "ivSelectGarageDecrementImageELEC")
  private WebElement btnDecrementImageEle;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name BEGINSWITH 'garage_selection_preference_ADA_minus_button_'")
  @AndroidFindBy(id = "ivSelectGarageDecrementImageADA")
  private WebElement btnDecrementImageAda;

  @iOSXCUITFindBy(id = "garage_selection_available_garage_cta_1")
  @AndroidFindBy(id = "tvGarageOption0")
  private WebElement firstGarageOption;

  @iOSXCUITFindBy(id = "garage_selection_available_garage_cta_2")
  @AndroidFindBy(id = "tvGarageOption1")
  private WebElement secondGarageOption;

  @iOSXCUITFindBy(id = "garage_selection_available_garage_cta_3")
  @AndroidFindBy(id = "tvGarageOption2")
  private WebElement thirdGarageOption;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name BEGINSWITH 'garage_selection_available_garage_cta' AND type == 'XCUIElementTypeButton'")
  @AndroidFindBy(uiAutomator = "resourceIdMatches(\"^tvGarageOption.*\")")
  private List<WebElement> allAvailableGarages;
}