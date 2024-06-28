package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AttendIssueNoParkingTicketScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "attend_issue_container_secondary_title")
  private WebElement secondaryTitle;

  @iOSXCUITFindBy(id = "user_name")
  private WebElement fanName;

  @iOSXCUITFindBy(id = "email_id")
  private WebElement fanEmail;

  @iOSXCUITFindBy(id = "information")
  private WebElement txtInformation;

  @iOSXCUITFindBy(id = "attend_issue_primary_button")
  private WebElement btnPrimary;

  @iOSXCUITFindBy(id = "attend_issue_secondary_button")
  private WebElement btnSecondary;
}
