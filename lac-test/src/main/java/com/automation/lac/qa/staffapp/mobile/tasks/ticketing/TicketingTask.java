package com.automation.lac.qa.staffapp.mobile.tasks.ticketing;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.enums.TypeOfPurchaseTicket;
import com.automation.lac.qa.staffapp.mobile.screens.ticketing.TicketingScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import java.util.stream.IntStream;
import org.openqa.selenium.WebElement;

public class TicketingTask extends TicketingScreen {

  @Step("Tap on available event")
  public void clickOnAvailableEvent() {
    getLstTicketsCards().stream().filter(WebElement::isEnabled).findAny()
      .orElseThrow(() -> new CustomException("Not available game or event")).click();
  }

  /**
   * Tap on ticket option
   *
   * @param typeOfPurchaseTicket for filter the options
   */
  @Step("Tap on ticket option {typeOfPurchaseTicket}")
  public void clickOnTicketOption(TypeOfPurchaseTicket typeOfPurchaseTicket) {
    int option = IntStream.range(0, getPurchaseBannerComponent().getLstTypeTickets().size()).filter(
        index -> containsIgnoreCase(
          getPurchaseBannerComponent().getLstTypeTickets().get(index).getAttribute("label"),
          typeOfPurchaseTicket.getLabel())).findFirst()
      .orElseThrow(() -> new CustomException("Type of ticket not found"));
    click(getPurchaseBannerComponent().getLstArrowButtons().get(option), "Type of ticket");
  }
}
