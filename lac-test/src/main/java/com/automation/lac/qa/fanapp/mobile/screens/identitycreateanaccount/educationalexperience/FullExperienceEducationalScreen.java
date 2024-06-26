package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.educationalexperience;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FullExperienceEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(id = "IntuitDomeIntroScreenbtnPrimary")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_primary_ds_button'")
  private WebElement btnLiveTheFullExperience;
}