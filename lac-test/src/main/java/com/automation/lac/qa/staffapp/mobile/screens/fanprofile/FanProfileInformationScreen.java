package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.FanProfileTabOptionsComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FanProfileInformationScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "fan_information_name_initials")
  private WebElement lblFanInitials;

  @iOSXCUITFindBy(id = "fan_name")
  private WebElement lblFanName;

  @iOSXCUITFindBy(id = "fan_last_name")
  private WebElement lblFanLastName;

  @iOSXCUITFindBy(id = "fan_chosen_name")
  private WebElement lblFanChosenName;

  @iOSXCUITFindBy(id = "see_personal_information_button")
  private WebElement btnExpandFanInformation;

  @iOSXCUITFindBy(id = "hide_personal_information_button")
  private WebElement btnHideFanInformation;

  @iOSXCUITFindBy(id = "chevron_down")
  private WebElement imgExpandFanInformation;

  @iOSXCUITFindBy(id = "chevron_up")
  private WebElement imgHideFanInformation;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_email_address' "
    + "AND type == 'XCUIElementTypeStaticText'")
  private WebElement lblFanEmail;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_phone_number' "
    + "AND type == 'XCUIElementTypeStaticText'")
  private WebElement lblFanPhoneNumber;

  @iOSXCUITFindBy(iOSNsPredicate = "name =='fan_address' AND type == 'XCUIElementTypeStaticText'")
  private WebElement lblFanAddress;

  @iOSXCUITFindBy(id = "mini_fan_box_minor_quantity_header")
  private WebElement lblTeamMatesTitle;

  @iOSXCUITFindBy(id = "no_minor_associated_yet_message")
  private WebElement msgNoTeammatesAssociated;

  @iOSXCUITFindBy(id = "mini_fan_box_add_minor_button")
  private WebElement btnAddTeammates;

  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND"
    + " name CONTAINS 'fan_information'")
  private List<WebElement> lstFanOptionsButtons;

  private FanProfileTabOptionsComponent fanProfileTabOptionsComponent;
}