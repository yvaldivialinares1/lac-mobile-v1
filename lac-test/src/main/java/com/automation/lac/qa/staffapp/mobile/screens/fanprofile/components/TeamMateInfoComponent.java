package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TeamMateInfoComponent extends Widget {

  @iOSXCUITFindBy(id = "mini_fan_list_name_and_surname")
  private WebElement lblSurname;

  @iOSXCUITFindBy(id = "mini_fan_list_chosen_name_title")
  private WebElement lblChosenName;

  @iOSXCUITFindBy(id = "mini_fan_list_date_of_birth_title")
  private WebElement lblDateOfBirth;

  @iOSXCUITFindBy(id = "see_mini_fan_details_button")
  private WebElement btnSeeDetails;

  protected TeamMateInfoComponent(WebElement element) {
    super(element);
  }
}
