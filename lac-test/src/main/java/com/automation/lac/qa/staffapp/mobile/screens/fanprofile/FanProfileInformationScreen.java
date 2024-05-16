package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FanProfileInformationScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "age_tag")
  private WebElement accountTypeLabel;

  @iOSXCUITFindBy(accessibility = "fan_information_name_initials")
  private WebElement fanInformationInitials;

  @iOSXCUITFindBy(accessibility = "fan_name")
  private WebElement fanInformationName;

  @iOSXCUITFindBy(accessibility = "fan_last_name")
  private WebElement fanInformationLastName;

  @iOSXCUITFindBy(accessibility = "fan_chosen_name")
  private WebElement fanInformationChosenName;

  @iOSXCUITFindBy(accessibility = "see_personal_information_button")
  private WebElement expandFanInformationButton;

  @iOSXCUITFindBy(accessibility = "hide_personal_information_button")
  private WebElement hideFanInformationButton;

  @iOSXCUITFindBy(accessibility = "chevron_down")
  private WebElement expandFanInformationImage;

  @iOSXCUITFindBy(accessibility = "chevron_up")
  private WebElement hideFanInformationImage;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,'fan_email_address')]")
  private WebElement fanInformationEmailAddress;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,'fan_phone_number')]")
  private WebElement fanInformationPhoneNumber;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,'fan_address')]")
  private WebElement fanInformationAddress;

  @iOSXCUITFindBy(accessibility = "mini_fan_box_minor_quantity_header")
  private WebElement miniFanLabelTitle;

  @iOSXCUITFindBy(accessibility = "no_minor_associated_yet_message")
  private WebElement noMiniFanAssociatedMessage;

  @iOSXCUITFindBy(accessibility = "mini_fan_box_add_minor_button")
  private WebElement addMiniFanButton;

  @iOSXCUITFindBy(accessibility = "fan_identity_label")
  private WebElement fanIdentityLabel;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeStaticText[@name='fan_vehicle_label' and @visible='true']")
  private WebElement fanVehicleLabel;

  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeButton[@name='fan_identity_add_vehicle_button' and @visible='true']")
  private WebElement addVehicleButton;

  @iOSXCUITFindBy(accessibility = "Fan haven’t added any vehicle yet")
  private WebElement noVehicleAddedMessage;

  @iOSXCUITFindBy(accessibility = "fan_identity_label_status")
  private WebElement fanIdentityStatusLabel;

  @iOSXCUITFindBy(accessibility = "fan_identity_game_face_title")
  private WebElement gameFaceIdRowTitle;

  @iOSXCUITFindBy(accessibility = "ADD")
  private WebElement addGameFaceIdButton;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='fan_tab_item_' and "
    + "contains(@label, 'Access')]")
  private WebElement fanProfileAccessTab;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='fan_tab_item_' and "
    + "contains(@label, 'In-Venue')]")
  private WebElement fanProfileInVenueXpTab;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='fan_tab_item_' and "
    + "contains(@label, 'Payments')]")
  private WebElement fanProfilePaymentsTab;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='fan_tab_item_' and "
    + "contains(@label, 'Loyalty')]")
  private WebElement fanProfileLoyaltyTab;

  @iOSXCUITFindBy(accessibility = "Age verification header")
  private WebElement ageVerificationRowTitle;

  @iOSXCUITFindBy(accessibility = "fan_identity_age_verification_verify_manually_button")
  private WebElement ageVerificationVerifyManuallyButton;

  @iOSXCUITFindBy(accessibility = "see_minor_list_button")
  private WebElement seeMiniFanListButton;

  @iOSXCUITFindBy(accessibility = "vehicle_row_data")
  private List<WebElement> vehicleListLicenseState;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'vehicle_info_result_list_')]")
  private List<WebElement> vehicleVehiclesScreenList;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'vehicle_info_result_list')]")
  private List<WebElement> vehicleFanInfoScreenList;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='vehicle_nick_name']")
  private List<WebElement> vehicleNickNameList;

  @iOSXCUITFindBy(accessibility = "fan_identity_game_face_remove_button")
  private WebElement removeGameFaceIdButton;

  @iOSXCUITFindBy(accessibility = "age_verification_state")
  private WebElement ageSource;

  @iOSXCUITFindBy(accessibility = "age_verification_source")
  private WebElement ageVerificationSource;

  @iOSXCUITFindBy(accessibility = "fan_age_verification_remove_button")
  private WebElement removeAgeVerificationButton;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='age_verification_state']")
  private WebElement ageVerificationStateLabel;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='age_verification_source']")
  private WebElement ageVerificationSourceLabel;

}