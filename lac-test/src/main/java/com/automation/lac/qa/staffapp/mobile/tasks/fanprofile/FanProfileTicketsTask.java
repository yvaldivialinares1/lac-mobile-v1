package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanTicketsScreen;
import com.automation.lac.qa.utils.CustomException;

public class FanProfileTicketsTask extends FanTicketsScreen {

  public void clickOnUpcomingTab() {
    click(getBtnUpcomingTab(), "Upcoming time tab");
  }

  public void clickOnPastTab() {
    click(getBtnPastTab(), "Past time tab");
  }

  /**
   * Click on ticket type filter
   *
   * @param ticketType for filter the tickets
   */
  public void clickOnTicketTypeFilter(String ticketType) {
    getLstFilterOptions().stream()
      .filter(filterOption -> containsIgnoreCase(filterOption.getAttribute("label"), ticketType))
      .findFirst().orElseThrow(() -> new CustomException("Type of ticket not found")).click();
  }
}
