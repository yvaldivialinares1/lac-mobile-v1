package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.educationalexperience;

import static org.apache.logging.log4j.util.Lazy.lazy;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.ImportantInfoEducationalComponent;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.function.Supplier;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CreateAccountEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"CreateInitialDomeAccountScreenbtnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name = 'CREATE INTUIT DOME ACCOUNT' AND type CONTAINS 'Button'")
  private WebElement btnCreateIntuitDomeAccount;

  public ImportantInfoEducationalComponent getImportantInfoEducationalComponent() {
    return importantInfoEducationalComponent.get();
  }

  private final Supplier<ImportantInfoEducationalComponent> importantInfoEducationalComponent =
    lazy(() -> {
      ImportantInfoEducationalComponent component = new ImportantInfoEducationalComponent();
      PageFactory.initElements(new AppiumFieldDecorator(getDriver()), component);
      return component;
    });
}