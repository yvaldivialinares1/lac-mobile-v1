package com.automation.lac.qa.staffapp.mobile.screens.gameface;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class GameFaceIdEducationalScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "skip_button")
  private WebElement btnSkip;

  @iOSXCUITFindBy(id = "faceId_logo")
  private WebElement faceIdLogo;

  @iOSXCUITFindBy(accessibility = "continue_game_face_id_button")
  private WebElement btnContinueWithGameFaceId;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'DO IT LATER'")
  private WebElement btnDoItLater;

  @iOSXCUITFindBy(accessibility = "StaffCheckbox_ImageStyle_100")
  private WebElement checkBoxAcknowledgement;
}