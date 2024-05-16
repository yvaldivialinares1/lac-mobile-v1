package com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_TEAMMATE_ACCOUNT;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.TeammateAccountsEducationalScreen;

public class TeammateAccountsEducationalTask extends TeammateAccountsEducationalScreen {

  public void clickAddTeammateAccount() {
    click(getBtnAddTeammate(), ADD_TEAMMATE_ACCOUNT.getValue());
  }
}