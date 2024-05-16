package com.automation.lac.qa.fanapp.mobile.screens.myprofile.component;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class PurchasedTicketComponent extends Widget {

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_tv_event_title\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'name_label'")
  private WebElement lblTicketName;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_tv_event_date\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'date_label'")
  private WebElement lblTicketDate;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*tickets_tv_ticket_count\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'ticket_count_label'")
  private WebElement lblTicketCount;

  protected PurchasedTicketComponent(WebElement element) {
    super(element);
  }
}