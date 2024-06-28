package com.automation.lac.qa.fanapp.mobile.screens.commons.components;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SEARCH;
import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class SelectCountryWidget extends Widget {

  public SelectCountryWidget(WebElement element) {
    super(element);
  }

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc, 'Search')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Search'")
  private WebElement inputSearchCountry;

  /**
   * Return xpath element for country to select
   * @param country to select
   * @return WebElement
   */
  private WebElement btnExpectedCountry(String country) {
    String androidXpath = "//android.view.View[contains(@content-desc, '%s')]";
    String iosXpath = "//XCUIElementTypeOther[contains(@label, '%s')]";
    String myXpath = isAndroid() ? androidXpath : iosXpath;
    return getDriver().findElement(By.xpath(String.format(myXpath, country)));
  }

  public void selectCountry(String country) {
    sendKeys(getInputSearchCountry(), country, SEARCH.getValue());
    click(btnExpectedCountry(country), country);
  }
}