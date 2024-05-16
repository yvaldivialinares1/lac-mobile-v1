package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import com.automation.lac.qa.fanapp.mobile.tasks.modals.ModalGoCashlessTasks;
import io.cucumber.java.en.And;

public class ModalStepsDefinition {

  protected ModalGoCashlessTasks modalGoCashlessTasks = new ModalGoCashlessTasks();

  @And("the user clicks on remind me later on go cashless modal")
  public void theUserClicksOnRemindMeLaterOnGoCashlessModal() {
    modalGoCashlessTasks.clickOnRemindMeLater();
  }
}