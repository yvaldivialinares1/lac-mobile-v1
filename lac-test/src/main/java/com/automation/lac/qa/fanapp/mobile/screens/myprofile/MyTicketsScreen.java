package com.automation.lac.qa.fanapp.mobile.screens.myprofile;

import com.automation.lac.qa.fanapp.mobile.screens.myprofile.component.PurchasedTicketComponent;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyTicketsScreen extends MobileBaseScreen {
  @AndroidFindBy(uiAutomator = "descriptionContains(\"UPCOMING\")")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'UPCOMING')]")
  private WebElement tabUpcoming;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"PAST\")")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'PAST')]")
  private WebElement tabPast;

  @AndroidFindBy(uiAutomator = "resourceId(\"com.laclippers.fanapp.stg:id/tickets_event_layout\")")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView/XCUIElementTypeStaticText")
  private List<PurchasedTicketComponent> listPurchasedTicket;

}
