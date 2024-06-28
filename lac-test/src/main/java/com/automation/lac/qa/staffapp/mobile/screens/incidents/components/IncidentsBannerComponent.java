package com.automation.lac.qa.staffapp.mobile.screens.incidents.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class IncidentsBannerComponent extends Widget {

  @iOSXCUITFindBy(id = "emergency_contact_incidents_title")
  private WebElement lblTitle;

  @iOSXCUITFindBy(id = "emergency_contact_close_button")
  private WebElement btnClose;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'emergency_contact_cell')]")
  private List<WebElement> lstIncidentsButtons;

  protected IncidentsBannerComponent(WebElement element) {
    super(element);
  }
}
