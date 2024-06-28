package com.automation.lac.qa.staffapp.mobile.questions.common;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.staffapp.mobile.screens.commons.FanSearchScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FanSearchQuestions extends FanSearchScreen {

  /**
   * Validate if the fan profile is found by email
   *
   * @param fanEmail for search the fan in the results
   */
  @Step("Validate if fan email is shown in the results {fanEmail}")
  public void isFanEmailShownInTheResults(String fanEmail) {
    if (getSearchResultsList().isEmpty()) {
      throw new CustomException("Fan profile not found");
    }
    getSearchResultsList().forEach(element -> log.info(element.getLblEmail().getText()));
    getSoftAssert().assertTrue(getSearchResultsList().stream().anyMatch(
        profile -> fanEmail.equalsIgnoreCase(profile.getLblEmail().getText())),
      "Fan profile found");
  }
}
