package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import com.automation.lac.qa.fanapp.mobile.tasks.modals.GoCashlessModalTask;
import io.cucumber.java.en.And;

public class ModalStepsDefinition {

  private final GoCashlessModalTask goCashlessModalTask = new GoCashlessModalTask();

  @And("the user clicks on remind me later on go cashless modal")
  public void theUserClicksOnRemindMeLaterOnGoCashlessModal() {
    goCashlessModalTask.clickOnRemindMeLater();
  }
}