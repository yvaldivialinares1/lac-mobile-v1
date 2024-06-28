package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.TicketCardComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.TicketInformationComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FanProfileInVenueXpScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "tickets_Label")
  private WebElement lblTicket;

  @iOSXCUITFindBy(id = "view_all_button")
  private WebElement btnViewAll;

  @iOSXCUITFindBy(id = "BUY A TICKET")
  private WebElement btnBuyATicket;

  private List<TicketCardComponent> lstTicketCardComponents;

  private final TicketInformationComponent ticketInformationComponent;

  public FanProfileInVenueXpScreen() {
    this.ticketInformationComponent = new TicketInformationComponent();
  }
}
