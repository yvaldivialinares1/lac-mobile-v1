package com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_TEAMMATE_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DATE_OF_BIRTH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.FIRST_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.LAST_NAME;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.faker.models.userinfo.TeammateInfo;
import com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts.AddTeammateAccountScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.commons.CalendarTask;
import java.util.List;

public class AddTeammateAccountTask extends AddTeammateAccountScreen {

  private final CalendarTask calendarTask = new CalendarTask();
  private final TeammateAccountsTask teammateAccountsTask = new TeammateAccountsTask();
  private final TeammateAccountsEducationalTask eduTask = new TeammateAccountsEducationalTask();

  /**
   * Add teammate account
   */
  public void addTeammateAccountInformation(List<TeammateInfo> teammatesList) {
    for (TeammateInfo mini : teammatesList) {
      if (teammatesList.indexOf(mini) == 0) {
        eduTask.clickAddTeammateAccount();
        enterMandatoryFields(mini);
      } else {
        teammateAccountsTask.clickAddTeammateAccount();
        enterMandatoryFields(mini);
      }
      click(getBtnAddTeammateAccount(), ADD_TEAMMATE_ACCOUNT.getValue());
    }
  }

  /**
   * Enters mandatory fields for mini account information
   */
  public void enterMandatoryFields(TeammateInfo miniAccountInfo) {
    completeFullName(miniAccountInfo.getFirstName(), miniAccountInfo.getLastName());
    completeDateOfBirth(miniAccountInfo);
  }

  /**
   * Workaround for iOS as text fields are not working as expected
   */
  public void completeFullName(String firstName, String lastName) {
    sendKeys(getInputFirstName(), firstName, FIRST_NAME.getValue());
    sendKeys(getInputLastName(), lastName, LAST_NAME.getValue());
  }

  /**
   * Completes the date of birth using CalendarTask, with provided birthdate
   */
  public void completeDateOfBirth(TeammateInfo miniAccountInfo) {
    click(getInputDateOfBirth(), DATE_OF_BIRTH.getValue());
    calendarTask.selectDate(miniAccountInfo.getBirthDate());
  }
}