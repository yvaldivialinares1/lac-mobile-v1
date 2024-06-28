package com.automation.lac.qa.allure;

import static com.automation.lac.qa.driver.AppiumConstants.APP_VERSION;
import static com.automation.lac.qa.driver.AppiumConstants.deviceName;
import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.driver.AppiumConstants.platformVersion;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import com.automation.lac.qa.environment.Environments;
import com.automation.lac.qa.listener.TestListener;
import com.automation.lac.qa.pages.MobileBaseScreen;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.io.ByteArrayInputStream;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Slf4j
public class AllureLogger {

  private static final String LOG_HEADER = "LA Clippers | ";
  private static final String NO_CONTENT = "Nothing to report!";
  private static final String BASE_PATH = System.getProperty("user.dir") + "/allure-results/";
  public static final String ENV =
    Environments.byName(System.getProperty("environment")).getName();

  /**
   * Log a no context message to the console. This is mainly used in the
   * TestNG configuration methods and listeners.
   */
  public void printLog() {
    printWrapper(getPrettifyMessage(NO_CONTENT));
  }

  /**
   * Log a message to the console.
   *
   * @param message to send
   */
  public void printLog(String message) {
    printWrapper(getPrettifyMessage(message));
  }

  /**
   * Log a message to the console and to the Allure report.
   *
   * @param message to send
   */
  public void printLogToReport(String message) {
    printToReport(message);
    printWrapper(getPrettifyMessage(message));
  }

  private String getPrettifyMessage(String rawMessage) {
    StackTraceElement trace = Thread.currentThread().getStackTrace()[3];
    String callingMethod =
      StringUtils.substringAfterLast(trace.getClassName(), ".") + "." + trace.getMethodName()
        + "()";
    return callingMethod + " | " + rawMessage;
  }

  @Step("{0}")
  private void printToReport(String prettyMessage) {
    // Shell method for annotation reader
  }

  private void printWrapper(String message) {
    log.info(LOG_HEADER + message);
  }

  /**
   * Center and decorate String into allure report
   *
   * @param value     String
   * @param decorator Character
   * @param maxLength int
   * @return String
   */
  public String decorateString(final String value, final Character decorator, final int maxLength) {
    StringBuilder output = new StringBuilder();
    if (value.length() <= maxLength) {
      int freeSpace = maxLength - value.length();
      boolean isEven = (freeSpace & 1) == 0;
      if (isEven) {
        appendDecorator(output, decorator, freeSpace);
      } else {
        for (int i = 0; i < (freeSpace * 0.5f) - 1; i++) {
          output.append(decorator);
        }
      }
      output.append(value);
      appendDecorator(output, decorator, freeSpace);
    } else {
      output.append(value);
    }
    return output.toString();
  }

  private static void appendDecorator(StringBuilder output, Character decorator, int freeSpace) {
    for (int i = 0; i < freeSpace * 0.5f; i++) {
      output.append(decorator);
    }
  }

  /**
   * print info into allure report
   *
   * @param message String
   */
  public void printBannerLog(String message) {
    printLog(decorateString("[" + message + "]", '-', 100));
  }

  /**
   * Create text Attachment into allure report
   *
   * @param title   String
   * @param content String
   * @return String
   */
  @Attachment(value = "{0}", type = "text/plain")
  public String createTextAttachment(String title, String content) {
    String response = title + ": \n\n" + content;

    printBannerLog(title);
    printLog(response);
    printBannerLog(title);
    return response;
  }

  public void deleteOldAllureReports() {
    File projectFile = new File("./allure-results");
    FileUtils.deleteQuietly(projectFile);
  }

  /**
   * Set environment variables into allure report
   */
  public void setEnvironment() {
    String testExecutionId = TestListener.getTestExecutionId();
    if (testExecutionId == null) {
      testExecutionId = "N/A";
    }
    allureEnvironmentWriter(ImmutableMap.<String, String>builder()
      .put("Environment", ENV)
      .put("Device Name", deviceName)
      .put("Device S.O Version", platformVersion)
      .put("Device S.O Name", platformName)
      .put("App Version", APP_VERSION)
      .put("Test Execution ID", testExecutionId)
      .build(), BASE_PATH);
  }

  /**
   *
   * @param name String
   */
  public static void attachScreenShot(String name) {
    log.info("Taking screenshot");
    try {
      byte[] screenshot =
        ((TakesScreenshot) MobileBaseScreen.getDriver()).getScreenshotAs(OutputType.BYTES);
      Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    } catch (Exception e) {
      log.error("Error taking screenshot : {}", e.getMessage());
    }

  }
}