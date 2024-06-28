package com.automation.lac.qa.staffapp.mobile.screens.ticketing.components;

import com.automation.lac.qa.pages.MobileBaseComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class PurchaseBannerComponent extends MobileBaseComponent {

  @iOSXCUITFindBy(id = "TypeOfTicketTitle")
  private WebElement lblHeaderTitle;

  @iOSXCUITFindBy(id = "TypeOfTicketClose")
  private WebElement btnClose;

  @iOSXCUITFindBy(id = "TypeOfTicketTitleHeader")
  private WebElement lblTicketTitle;

  @iOSXCUITFindBy(id = "TypeOfTicketDate")
  private WebElement lblTicketDate;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'TypeOfTicketItem'")
  private List<WebElement> lstTypeTickets;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'arrow-right-s-line'")
  private List<WebElement> lstArrowButtons;
}
