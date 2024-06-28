package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FinishIssueScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,'radio_circle')]")
  private List<WebElement> optionsList;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'other_reason_identifier' AND type == 'XCUIElementTypeTextField'")
  WebElement finishIssueReasonInput;

  @iOSXCUITFindBy(accessibility = "cancel")
  private WebElement btnCancel;

  @iOSXCUITFindBy(accessibility = "confirm_button_identifier")
  private WebElement btnConfirm;
}
