package com.automation.lac.qa.fanapp.mobile.tasks.modals;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.REMIND_ME_LATER;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.fanapp.mobile.screens.modals.GoCashlessModal;

public class GoCashlessModalTask extends GoCashlessModal {

  public void clickOnAddCard() {
    click(getBtnAddCard(), ADD_CARD.getValue());
  }

  public void clickOnRemindMeLater() {
    if (isTheElementVisible(getBtnRemindMeLater(), 20))
      click(getBtnRemindMeLater(), REMIND_ME_LATER.getValue());
  }
}
