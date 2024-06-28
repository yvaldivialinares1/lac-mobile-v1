package com.automation.lac.qa.staffapp.mobile.tasks.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.staffapp.mobile.screens.access.FinishIssueScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class FinishIssueTask extends FinishIssueScreen {

  /**
   * select finish issue option by value.
   *
   * @param option String value of option.
   * @return FinishIssueTask
   */
  @Step("select finish issue option as {option}")
  public FinishIssueTask selectOptionFromSendIssueToBoxOfficeDropdown(String option) {
    WebElement element = getOptionsList().stream()
      .filter(el -> option.contains(el.getAttribute("label")))
      .findFirst()
      .orElseThrow(() -> new CustomException(
        "No such option presented in the list as: " + option));
    click(element, option);
    return this;
  }

  /**
   * type finish issue reason.
   *
   * @param reason String text of reason.
   * @return FinishIssueTask
   */
  public FinishIssueTask typeIntoFinishIssueReasonInput(String reason) {
    sendKeys(getFinishIssueReasonInput(), reason, "type into");
    hideKeyboard("done");
    return this;
  }

  /**
   * tap form confirmation button.
   *
   * @return FinishIssueTask
   */
  public FinishIssueTask confirmFinishIssueForm() {
    click(getBtnConfirm(), getBtnConfirm().getAttribute("label"));
    return this;
  }
}
