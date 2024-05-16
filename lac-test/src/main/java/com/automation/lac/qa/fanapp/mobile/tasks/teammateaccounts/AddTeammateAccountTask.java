package com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_TEAMMATE_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CURRENT_SELECTION_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DATE_OF_BIRTH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DATE_PICKER;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.HIDE_YEAR_PICKER;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.NEXT_MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.POPOVER_DISMISS_CALENDAR;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PREVIOUS_MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.DAY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.FIRST_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.LAST_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.YEAR;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.APRIL;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.AUGUST;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.DECEMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.FEBRUARY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.JANUARY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.JULY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.JUNE;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.MARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.MAY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.NOVEMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.OCTOBER;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.SEPTEMBER;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.getElement;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementBeClickable;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.faker.models.MiniAccountInfo;
import com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts.AddTeammateAccountScreen;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

@Slf4j
public class AddTeammateAccountTask extends AddTeammateAccountScreen {

  /**
   * Complete all the Mini Account Information and sets Mini Account info as used
   */
  public List<MiniAccountInfo> completeTeammateAccountInformation(
                                                      List<MiniAccountInfo> miniAccountsList,
                                                      MiniAccountInfo miniAccountInfo) {
    enterMandatoryFields(miniAccountInfo);
    hideKeyboard("done");
    click(getBtnAddTeammateAccount(), ADD_TEAMMATE_ACCOUNT.getValue());
    return setTeammaetAccountAsUsed(miniAccountsList, miniAccountInfo);
  }

  /**
   * Enters mandatory fields for mini account information
   */
  public void enterMandatoryFields(MiniAccountInfo miniAccountInfo) {
    completeFullName(miniAccountInfo.getFirstName(), miniAccountInfo.getLastName());
    log.info(String.format("Date of Birth: %s", miniAccountInfo.getBirthDate()));
    completeDateOfBirth(miniAccountInfo);
  }

  public void completeFullName(String firstName, String lastName) {
    sendKeys(getInputFirstName(), firstName, FIRST_NAME.getValue());
    sendKeys(getInputLastName(), lastName, LAST_NAME.getValue());
  }

  /**
   * Completes the date of birth field with the provided birthdate
   * Hides the calendar and dismisses the keyboard after selecting the date
   * On Android platform, clicks on the Confirm button; on iOS platform,
   * clicks on the Hide Calendar button
   */
  public void completeDateOfBirth(MiniAccountInfo miniAccountInfo) {
    String month = miniAccountInfo.getBirthDate().getMonth().toString();
    String day = String.valueOf(miniAccountInfo.getBirthDate().getDayOfMonth());
    String year = String.valueOf(miniAccountInfo.getBirthDate().getYear());
    selectDateOfBirth(month, day, year);

    if (isAndroid()) {
      click(getBtnConfirm(), CONFIRM.getValue());
    } else {
      click(getBtnPopoverDismissCalendar(), POPOVER_DISMISS_CALENDAR.getValue());
    }
    hideKeyboard(null);
  }

  /**
   * Selects the date of birth with the provided month, day, and year
   */
  public void selectDateOfBirth(String month, String day, String year) {
    waitForElementBeClickable(1, getInputDateOfBirth());
    click(getInputDateOfBirth(), DATE_OF_BIRTH.getValue());
    month = StringUtils.capitalize(month.toLowerCase());
    if (isAndroid()) {
      setDateOfBirthAndroid(month, day, year);
    } else {
      setDateOfBirthIos(month, day, year);
    }
  }

  /**
   * Sets the date of birth for Android devices with the provided month, day, and year
   */
  public void setDateOfBirthAndroid(String month, String day, String year) {
    int i = 0;
    String monthToSelect = calculateMonthDistance(month);
    while (getTxtCurrentSelectionDate().isDisplayed()
            && !getTxtCurrentSelectionDate().getText().contains(month) && i < 12) {
      if (monthToSelect.equals("Next")) {
        click(getBtnNextMonth(), NEXT_MONTH.getValue());
      } else if (monthToSelect.equals("Previous")) {
        click(getBtnPreviousMonth(), PREVIOUS_MONTH.getValue());
      }
      i++;
    }
    click(getBtnSwitchToYearSelection(), DATE_PICKER.getValue());

    String yearXpath = String.format(YEAR_XPATH_ANDROID, year);
    click(getDriver().findElement(By.xpath(yearXpath)), String.format("Select year '%s' ", year));

    String dayToSearch = month.concat(" ").concat(day).concat(", ").concat(year);
    String dayXpathToSearch = String.format(DAY_XPATH_ANDROID, dayToSearch);
    click(getElement(By.xpath(dayXpathToSearch)), String.format("Select day %s", dayToSearch));
  }

  /**
   * Set date of birth for iOS
   *
   * @param month month of birth
   * @param day   day of birth
   * @param year  year of birth
   */
  public void setDateOfBirthIos(String month, String day, String year) {
    String defaultDate = getTxtCurrentSelectionDate().getAttribute("value");
    click(getTxtCurrentSelectionDate(), CURRENT_SELECTION_DATE.getValue());
    sendKeys(getYearIos(defaultDate), year, YEAR.getValue());
    sendKeys(getMonthIos(defaultDate), month, MONTH.getValue());
    click(getHideYearPicker(), HIDE_YEAR_PICKER.getValue());
    sendKeys(getDayIos(day), day, DAY.getValue());
  }

  /**
   * Decides which is the shortest path to expected month based on actual month
   *
   * @param month String
   * @return path
   */
  private String calculateMonthDistance(String month) {
    LocalDate currentDate = LocalDate.now();
    List<String> months = List.of(
            JANUARY.getDescription(), FEBRUARY.getDescription(), MARCH.getDescription(),
            APRIL.getDescription(), MAY.getDescription(), JUNE.getDescription(),
            JULY.getDescription(), AUGUST.getDescription(), SEPTEMBER.getDescription(),
            OCTOBER.getDescription(), NOVEMBER.getDescription(), DECEMBER.getDescription()
    );

    String currentMonth = StringUtils.capitalize(currentDate.getMonth().name().toLowerCase());

    if (month.equals(currentMonth)) {
      return "Actual";
    } else {
      int startIndex = months.indexOf(currentMonth);
      int targetIndex = months.indexOf(month);
      int counterNext = (startIndex - targetIndex + 12) % 12;
      int countPrevious = (targetIndex - startIndex + 12) % 12;

      if (counterNext > countPrevious) {
        return "Next";
      } else {
        return "Previous";
      }
    }
  }

  /**
   * Set mini account as used
   * @param miniAccountsList - mini account list from test context
   * @param miniAccount      - mini account to be updated
   * @return updated list
   */
  public List<MiniAccountInfo> setTeammaetAccountAsUsed(List<MiniAccountInfo> miniAccountsList,
                                                        MiniAccountInfo miniAccount) {
    miniAccountsList.remove(miniAccount);
    miniAccount.setUsed(true);
    miniAccountsList.add(miniAccount);
    return miniAccountsList;
  }
}