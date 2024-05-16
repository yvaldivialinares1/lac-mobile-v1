package com.automation.lac.qa.fanapp.mobile.screens.educationals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class GameFaceIdEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"GameFaceId,\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_image' AND label BEGINSWITH 'game face'")
  private WebElement imgGameFaceIdLogo;

  @AndroidFindBy(uiAutomator = "resourceId(\"GameFaceEducationalScreentvTitle\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_label_title' AND label BEGINSWITH 'game face'")
  //@iOSXCUITFindBy(iOSNsPredicate = "name == \"id_label_title\"")
  private WebElement lblSetGameFaceIdTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"GameFaceEducationalScreenbtnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'GAME FACE' AND type CONTAINS 'Button'")
  private WebElement btnSetGameFaceId;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND  type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(uiAutomator = "text(\"Skip\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_navigation_option_skip_with_text'")
  private WebElement btnSkip;
}