package com.automation.lac.qa.utils;

import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.SCREENSHOTS_FOLDER;

import com.automation.lac.qa.pages.MobileBaseScreen;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Slf4j
@UtilityClass
public class MagnifAiUtils {
  /**
   * Generate a test name with a time stamp
   *
   * @param testType String with the type of test e.g: flexCompareImage
   * @return String of a test type with a time stamp in format: testType_yyyyMMddHHmmss
   */
  public static String generateTestName(String testType) {
    Date currentTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String formattedTime = dateFormat.format(currentTime);

    return testType + "_" + formattedTime;
  }

  /**
   * Get execution ID from lac.properties file
   *
   * @return String of execution ID based on execution platform
   */
  public static String getExecutionId() {
    if (platformName.contains("Android")) {
      return PropertiesManager.getParameter("framework.magnifai.android.executionid");
    } else {
      return PropertiesManager.getParameter("framework.magnifai.ios.executionid");
    }
  }

  /**
   * This function takes a full screenshot of the current screen
   * the screenshots are saved in the folder: test/resoruces/magnifai/actual
   *
   * @param fileName the name of the screenshot you want to save
   * @return File image saved
   */
  public static File takeMagnifAiScreenshot(String fileName) {
    File screenshotFile = null;
    try {
      screenshotFile =
        ((TakesScreenshot) MobileBaseScreen.getDriver()).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(screenshotFile, new File(SCREENSHOTS_FOLDER.getText() + fileName));
    } catch (Exception e) {
      log.error("Error taking screenshot : {}", e.getMessage());
    }
    return screenshotFile;
  }

  /**
   * Generates a random screenshot file name with the format 'screenshot-{randomString}.png'.
   *
   * @return A random screenshot file name.
   */
  public static String generateScreenshotFileName() {
    Faker faker = new Faker();
    String randomString = faker.regexify("[A-Za-z]{6}");
    return String.format("screenshot-%s.png", randomString);
  }
}
