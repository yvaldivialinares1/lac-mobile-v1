package com.automation.lac.qa.fanapp.mobile.screens.initialpermissions;

import static org.apache.logging.log4j.util.Lazy.lazy;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.AllowNotificationsComponent;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.EnableLocationComponent;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.function.Supplier;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class TurnOnNotificationScreen extends MobileBaseScreen {

  @AndroidFindBy(accessibility = "CONTINUE")
  @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"CONTINUE\"`]")
  private WebElement btnContinue;

  /**
   * Supplier that provides a lazily-initialized instance of {@link AllowNotificationsComponent}.
   * The component is initialized with {@link PageFactory} using {@link AppiumFieldDecorator}
   * to decorate its fields, which means that the fields annotated with Appium's FindBy annotations
   * will be located using the provided Appium driver when the component is actually used.
   *
   * @see AllowNotificationsComponent
   * @see PageFactory
   * @see AppiumFieldDecorator
   */
  private final Supplier<AllowNotificationsComponent> allowNotificationsComponent =
    lazy(() -> {
      AllowNotificationsComponent component = new AllowNotificationsComponent();
      PageFactory.initElements(new AppiumFieldDecorator(getDriver()), component);
      return component;
    });
  /**
   * Supplier that provides a lazily-initialized instance of {@link EnableLocationComponent}.
   * Similar to {@link #allowNotificationsComponent}, this supplier ensures that the
   * {@link EnableLocationComponent} is only created and initialized with {@link PageFactory}
   * when it is needed, using {@link AppiumFieldDecorator} to properly initialize the fields
   * of the component.
   *
   * @see EnableLocationComponent
   * @see PageFactory
   * @see AppiumFieldDecorator
   */
  private final Supplier<EnableLocationComponent> enableLocationComponent =
    lazy(() -> {
      EnableLocationComponent component = new EnableLocationComponent();
      PageFactory.initElements(new AppiumFieldDecorator(getDriver()), component);
      return component;
    });

  public AllowNotificationsComponent getAllowNotificationComponent() {
    return allowNotificationsComponent.get();
  }

  public EnableLocationComponent getEnableLocationComponent() {
    return enableLocationComponent.get();
  }
}