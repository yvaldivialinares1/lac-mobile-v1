package com.automation.lac.qa.fanapp.api.tasks;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.ATTACHMENT_NAME;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.FLEXIBLE_COMPARE_NAME;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.FLEXIBLE_SEARCH_NAME;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.FOUND;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.IMAGE_FOLDER;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.MIN_SIMILARITY;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.PASSED;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.SCREENSHOTS_FOLDER;
import static com.automation.lac.qa.magnifai.enums.MagnifAiData.TEST_MODE;
import static com.automation.lac.qa.utils.MagnifAiUtils.generateTestName;
import static com.automation.lac.qa.utils.MagnifAiUtils.getExecutionId;
import static com.automation.lac.qa.utils.MagnifAiUtils.takeMagnifAiScreenshot;

import com.automation.lac.qa.magnifai.MagnifAiApi;
import com.automation.lac.qa.magnifai.models.response.FlexibleImageComparisonResponse;
import com.automation.lac.qa.magnifai.models.response.FlexibleSearchResponse;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import lombok.experimental.UtilityClass;


@UtilityClass
public class MagnifAiTasks {

  /**
   * This functions calls MagnifAI API function, it searches childImage inside the parentImage.
   *
   * @param parentImage parent image name ending with extension .png
   * @param childImage  parent image name ending with extension .png
   */
  public void flexibleSearchImage(String parentImage, List<String> childImage) {
    takeMagnifAiScreenshot(parentImage);
    childImage.forEach(image ->
      Allure.step("Flexible search image:" + image, step -> {
        File parentImageFile = new File(SCREENSHOTS_FOLDER.getText() + parentImage);
        File childImageFile = new File(IMAGE_FOLDER.getText() + image);
        FlexibleSearchResponse flexibleSearchImage = MagnifAiApi
          .flexibleSearchImage(generateTestName(FLEXIBLE_SEARCH_NAME.getText()), getExecutionId(),
            parentImageFile, childImageFile, TEST_MODE.getText());
        Allure.addAttachment(ATTACHMENT_NAME.getText() + image, new ByteArrayInputStream(
          MagnifAiApi.getAsset(flexibleSearchImage.getFlexSearch().getResultImageRef())));
        boolean result = flexibleSearchImage.getStatus().equals(FOUND.getText());
        getSoftAssert().assertTrue(result, "Flexible image search");
      })
    );
  }

  /**
   * This functions calls MagnifAI API function, it compares baselineImage with inputImage.
   *
   * @param baselineImage baseline image name ending with extension .png
   * @param inputImage    input image name ending with extension .png
   */
  public void flexibleCompareImage(String baselineImage, List<String> inputImage) {
    takeMagnifAiScreenshot(baselineImage);
    inputImage.forEach(image ->
      Allure.step("Flexible compare images: " + image, stepContext -> {
        File baseImage = new File(SCREENSHOTS_FOLDER.getText() + baselineImage);
        File inpImage = new File(IMAGE_FOLDER.getText() + image);
        FlexibleImageComparisonResponse flexibleImageComparisonResponse = MagnifAiApi
          .flexibleImageComparison(generateTestName(FLEXIBLE_COMPARE_NAME.getText()),
            getExecutionId(), baseImage,
            inpImage, MIN_SIMILARITY.getText(), TEST_MODE.getText());
        Allure.addAttachment(ATTACHMENT_NAME.getText() + image, new ByteArrayInputStream(
          MagnifAiApi.getAsset(flexibleImageComparisonResponse.getFlexCompare().getResultImage())));
        boolean result = flexibleImageComparisonResponse.getStatus().equals(PASSED.getText());
        getSoftAssert().assertTrue(result, "Flexible image comparison");
      })
    );
  }
}
