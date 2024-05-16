package com.automation.lac.qa.recording;

import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_PLATFORM;
import static com.automation.lac.qa.driver.AppiumConstants.HUB_URL;
import static com.automation.lac.qa.driver.AppiumConstants.IOS_PLATFORM;
import static com.automation.lac.qa.driver.AppiumConstants.VIDEO_RECORDING;
import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

@Slf4j
@UtilityClass
public class Recording {

  private static final boolean VIDEO_RECORDING_ENABLED = Boolean.parseBoolean(VIDEO_RECORDING);
  private static final int RECORDING_TIME_LIMIT = 600;

  /**
   * Start video recording
   */
  public static void startRecording() {
    if (VIDEO_RECORDING_ENABLED && !HUB_URL.contains("browserstack")) {
      log.info("Start recording");
      if (isAndroid()) {
        ((AndroidDriver) getDriver()).startRecordingScreen(new AndroidStartScreenRecordingOptions()
                .withTimeLimit(Duration.ofSeconds(RECORDING_TIME_LIMIT))
                .withBitRate(300000));
      } else {
        ((IOSDriver) getDriver()).startRecordingScreen(new IOSStartScreenRecordingOptions()
                .withTimeLimit(Duration.ofSeconds(RECORDING_TIME_LIMIT))
                .withVideoType("mpeg4"));
      }
    }
  }

  /**
   * Stop video recording and saving video where indicated
   */
  public static void stopRecording(String scenarioName) {
    if (VIDEO_RECORDING_ENABLED && !HUB_URL.contains("browserstack")) {
      byte[] decode;
      if (isAndroid()) {
        decode = Base64.getDecoder().decode(((AndroidDriver) getDriver()).stopRecordingScreen());
      } else {
        decode = Base64.getDecoder().decode(((IOSDriver) getDriver()).stopRecordingScreen());
      }
      log.info("Stop recording");

      String workingDirectory = System.getProperty("user.dir");
      Path projectPath = Paths.get(workingDirectory).getParent().getParent().toAbsolutePath();
      String platform = isAndroid() ? ANDROID_PLATFORM : IOS_PLATFORM;
      DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
      String date = dateFormat.format(new Date());

      StringBuilder evidencePath = new StringBuilder(projectPath.toString());
      evidencePath.append(File.separator)
              .append(String.format("%s_Evidence", platform))
              .append(File.separator)
              .append(date);

      StringBuilder videoName = new StringBuilder(evidencePath);
      videoName.append(File.separator)
              .append(scenarioName)
              .append(".mp4");

      try {
        FileUtils.writeByteArrayToFile(new File(videoName.toString()), decode);
        log.info("You can find your video on -> {}", evidencePath);
      } catch (IOException ioException) {
        log.error("Unable to create video");
      }
    }
  }
}