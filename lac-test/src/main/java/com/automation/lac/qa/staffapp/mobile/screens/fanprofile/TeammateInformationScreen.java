package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.FanProfileClipperBandComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TeammateInformationScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "minor_description_back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "Edit")
  private WebElement btnEdit;

  @iOSXCUITFindBy(id = "mini_fan_description_name_and_surname_header")
  private WebElement lblSurname;

  @iOSXCUITFindBy(id = "mini_fan_description_chosen_name_title")
  private WebElement lblChosenName;

  @iOSXCUITFindBy(id = "mini_fan_description_date_of_birth_title")
  private WebElement lblDateOfBirth;

  @iOSXCUITFindBy(id = "mini_fan_description_date_of_birth_title")
  private WebElement btnSeeDetails;

  private FanProfileClipperBandComponent fanProfileClipperBandComponent;
}
