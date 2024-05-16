package com.automation.lac.qa.fanapp.mobile.screens.myprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class PurchasedTicketDetailsScreen extends MobileBaseScreen {
  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_tv_gate\")")
  @iOSXCUITFindBy(xpath = "(//*[contains(@name,'Ticket Type')])[last()-1]")
  private WebElement lblUserType;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_desc_right_bar_section\")")
  //TODO: need to ask dev to add locator for seat section
  @iOSXCUITFindBy(xpath = "TBD")
  private WebElement lblSeatSection;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_desc_center_bar_row\")")
  //TODO: need to ask dev to add locator for seat row
  @iOSXCUITFindBy(xpath = "TBD")
  private WebElement lblSeatRow;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_desc_left_bar_seat\")")
  //TODO: need to ask dev to add locator for seat number
  @iOSXCUITFindBy(xpath = "TBD")
  private WebElement lblSeatNumber;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_view_title_bar\")")
  @iOSXCUITFindBy(xpath = "(//*[contains(@name,'info_event_name')])[last()-1]")
  private WebElement lblTicketName;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_view_description_bar\")")
  @iOSXCUITFindBy(xpath = "(//*[contains(@name,'info_event_date')])[last()-1]")
  private WebElement lblTicketDate;
}
