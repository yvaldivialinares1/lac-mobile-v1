package com.automation.lac.qa.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.Widget;
import org.openqa.selenium.support.PageFactory;

/**
 * @deprecated This class is obsolete and will be removed in future versions.
 *             Please prioritize using {@link Widget} instead.
 */
@Deprecated
public abstract class MobileBaseComponent extends MobileBaseScreen {
  /**
   * Protected constructor that initializes page elements using Appium.
   */
  protected MobileBaseComponent() {
    super();
    PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
  }
}
