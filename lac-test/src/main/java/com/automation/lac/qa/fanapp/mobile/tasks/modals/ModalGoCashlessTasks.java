package com.automation.lac.qa.fanapp.mobile.tasks.modals;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.REMIND_ME_LATER;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.modals.ModalGoCashlessScreen;

public class ModalGoCashlessTasks extends ModalGoCashlessScreen {

  public void clickOnAddCard() {
    click(getBtnAddCard(), ADD_CARD.getValue());
  }

  public void clickOnRemindMeLater() {
    click(getBtnRemindMeLater(), REMIND_ME_LATER.getValue());
  }
}
