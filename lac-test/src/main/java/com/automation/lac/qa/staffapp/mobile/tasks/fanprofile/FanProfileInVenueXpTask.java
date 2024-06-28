package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfileInVenueXpScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;

public class FanProfileInVenueXpTask extends FanProfileInVenueXpScreen {

  public void clickOnViewAllButton() {
    click(getBtnViewAll(), "View all");
  }

  public void clickOnUpcomingTab() {
    click(getTicketInformationComponent().getBtnUpcomingTab(), "Upcoming time tab");
  }

  public void clickOnPastTab() {
    click(getTicketInformationComponent().getBtnPastTab(), "Past time tab");
  }

  /**
   * Click on ticket type filter
   *
   * @param ticketType for filter the tickets
   */
  @Step("Tap on ticket of type {ticketType}")
  public void clickOnTicketTypeFilter(String ticketType) {
    getTicketInformationComponent().getLstFilterOptions().stream()
      .filter(filterOption -> containsIgnoreCase(filterOption.getAttribute("label"), ticketType))
      .findFirst().orElseThrow(() -> new CustomException("Type of ticket not found")).click();
  }

  public void clickOnBuyATicket() {
    click(getBtnBuyATicket(), "Buy a ticket");
  }
}
