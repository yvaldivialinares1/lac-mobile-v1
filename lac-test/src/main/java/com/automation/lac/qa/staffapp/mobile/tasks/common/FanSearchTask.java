package com.automation.lac.qa.staffapp.mobile.tasks.common;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.staffapp.mobile.screens.commons.FanSearchScreen;
import com.automation.lac.qa.staffapp.mobile.screens.commons.components.FanSearchResultComponent;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;

public class FanSearchTask extends FanSearchScreen {

  /**
   * Type into manual search input.
   *
   * @param text String search text value.
   * @return ManualSearchInputTask
   */
  @Step("enter value into manual search input as {text}")
  public FanSearchTask enterInputInManualSearchField(String text) {
    waitForElementToBeClickable(getFrmInputManualSearch(), 5);
    click(getFrmInputManualSearch(), "activate search input field");
    sendKeys(getFrmInputManualSearch(), text, "manual search");
    return this;
  }

  /**
   * Find the specific fan from the result list and tap on a fan record.
   */
  @Step("select the note from search results list with email as {email}")
  public void selectFanAccountByEmail(String email) {
    FanSearchResultComponent result = getSearchResultsList().stream()
      .filter(card -> card.getLblEmail().getAttribute("label").equals(email))
      .findFirst()
      .orElseThrow(() -> new CustomException("No such result item with the value as: " + email));
    click(result.getLblEmail(), email);
  }
}
