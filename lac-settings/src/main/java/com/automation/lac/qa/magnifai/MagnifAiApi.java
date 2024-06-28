package com.automation.lac.qa.magnifai;

import static com.automation.lac.qa.magnifai.enums.MagnifAiEndPoints.ASSETS;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.magnifai.enums.MagnifAiEndPoints;
import com.automation.lac.qa.magnifai.enums.MagnifAiStaticParams;
import com.automation.lac.qa.magnifai.models.request.CreateExecutionRequest;
import com.automation.lac.qa.magnifai.models.request.FlexibleImageComparisonRequest;
import com.automation.lac.qa.magnifai.models.request.FlexibleLocateImageRequest;
import com.automation.lac.qa.magnifai.models.request.FlexibleSearchImageRequest;
import com.automation.lac.qa.magnifai.models.request.GetTokenRequest;
import com.automation.lac.qa.magnifai.models.request.ImageComparisonRequest;
import com.automation.lac.qa.magnifai.models.request.LocateImageRequest;
import com.automation.lac.qa.magnifai.models.request.SearchImageRequest;
import com.automation.lac.qa.magnifai.models.response.CreateExecutionResponse;
import com.automation.lac.qa.magnifai.models.response.FlexibleImageComparisonResponse;
import com.automation.lac.qa.magnifai.models.response.FlexibleLocateImageResponse;
import com.automation.lac.qa.magnifai.models.response.FlexibleSearchResponse;
import com.automation.lac.qa.magnifai.models.response.GetTokenResponse;
import com.automation.lac.qa.magnifai.models.response.ImageComparisonResponse;
import com.automation.lac.qa.magnifai.models.response.LocateImageResponse;
import com.automation.lac.qa.magnifai.models.response.SearchImageResponse;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.PropertiesManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class MagnifAiApi {

  private static final String BASE_URL =
    PropertiesManager.getParameter("framework.magnifai.baseurl");
  private static final String AUTH_URL =
    PropertiesManager.getParameter("framework.magnifai.authurl");
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final long TOKEN_REFRESH_INTERVAL_MINUTES = 5;
  private static LocalDateTime lastTokenTime;
  private static GetTokenResponse lastTokenResponse;
  private static final String USERNAME =
    PropertiesManager.getParameter("framework.magnifai.username");
  private static final String PASSWORD =
    PropertiesManager.getParameter("framework.magnifai.password");

  /**
   * Retrieves or refreshes the authentication token if it was obtained more than 5 minutes ago.
   *
   * @return The authentication token.
   */
  public static String refreshTokenIfNeeded() {
    if (lastTokenTime == null || lastTokenResponse == null
      || ChronoUnit.MINUTES.between(lastTokenTime, LocalDateTime.now())
      >= TOKEN_REFRESH_INTERVAL_MINUTES) {
      lastTokenResponse = getToken(USERNAME, PASSWORD);
      lastTokenTime = LocalDateTime.now();
    }
    return lastTokenResponse.getAccessToken();
  }

  /**
   * This method sends a request to the authentication endpoint to obtain a token that
   * can be used for subsequent API calls.
   *
   * @return A GetTokenResponse object containing the authentication token and related details.
   * @throws RuntimeException If any error occurs during the creation of the request,
   *                          the execution of the post call,
   *                          or the parsing of the response.
   */
  @Step("Get MagnifAI token")
  public static GetTokenResponse getToken(String username, String password) {

    GetTokenRequest requestObject = GetTokenRequest.builder()
      .clientId(MagnifAiStaticParams.CLIENT_ID.getText())
      .username(username)
      .password(password)
      .grantType(MagnifAiStaticParams.GRANT_TYPE.getText())
      .build();

    Request request = prepareFormParamsRequest(requestObject);
    Response response = request.baseUri(AUTH_URL)
      .post(MagnifAiEndPoints.GET_TOKEN.getText());
    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected get token StatusCode");
    return response.getResponse().as(GetTokenResponse.class);
  }


  /**
   * This method allows the creation of a new execution
   *
   * @param executionName The name of the execution to be created.
   * @param projectId     The ID of the project under which the execution is to be created.
   * @return CreateExecutionResponse Object contains information about the newly created execution.
   */
  @Step("Create execution Using execution name: {0} And the project ID: {1}")
  public static CreateExecutionResponse createExecution(String executionName, String projectId) {

    CreateExecutionRequest requestObject = CreateExecutionRequest.builder()
      .name(executionName)
      .projectId(projectId)
      .build();

    Request request = prepareJsonRequest(requestObject);
    Response response = request.baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .post(MagnifAiEndPoints.CREATE_EXECUTION.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_CREATED,
      "Unexpected MagnifAi Create execution StatusCode");

    return response.getResponse().as(CreateExecutionResponse.class);
  }

  /**
   * This method allows to compare two images.
   *
   * @param testName      The name of the test associated with the image comparison.
   * @param executionId   A unique identifier for the execution context of the comparison.
   * @param baselineImage The file system path to the baseline image.
   * @param inputImage    The file system path to the input image to compare against the baseline.
   * @param minSimilarity The minimum similarity percentage required for a match.
   * @param noiseFilter   The level of noise filtering to apply during comparison.
   * @return An ImageComparisonResponse object containing the results of the comparison.
   */
  @Step("Image Comparison Using execution ID: {1} And the test name: {0}")
  public static ImageComparisonResponse imageComparison(String testName, String executionId,
                                                        File baselineImage, File inputImage,
                                                        String minSimilarity, String noiseFilter) {

    ImageComparisonRequest requestObject = ImageComparisonRequest.builder()
      .testName(testName)
      .executionId(executionId)
      .baselineImage(baselineImage)
      .inputImage(inputImage)
      .minSimilarity(minSimilarity)
      .noiseFilter(noiseFilter)
      .build();

    Request request = prepareFormParamsRequest(requestObject);
    Response response = request.baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .post(MagnifAiEndPoints.IMAGE_COMPARISON.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected MagnifAi image comparison StatusCode");

    return response.getResponse().as(ImageComparisonResponse.class);
  }

  /**
   * This method performs a flexible image comparison between a baseline image and an input image.
   *
   * @param testName      The name of the test associated with the image comparison.
   * @param executionId   A unique identifier for the execution context of the comparison.
   * @param baselineImage The file system path to the baseline image.
   * @param inputImage    The file system path to the input image to compare against the baseline.
   * @param minSimilarity The minimum similarity percentage required for a match.
   * @param testMode      The mode of the test that may alter the comparison behavior or criteria.
   * @return A FlexibleImageComparisonResponse object containing the results of the comparison.
   * @throws RuntimeException If any error occurs during request creation, execution,
   *                          or response parsing.
   */
  @Step("Flex Image Comparison Using execution ID: {1} And the test name: {0}")
  public static FlexibleImageComparisonResponse flexibleImageComparison(String testName,
                                                                        String executionId,
                                                                        File baselineImage,
                                                                        File inputImage,
                                                                        String minSimilarity,
                                                                        String testMode) {

    FlexibleImageComparisonRequest requestObject = FlexibleImageComparisonRequest.builder()
      .testName(testName)
      .executionId(executionId)
      .baselineImage(baselineImage)
      .inputImage(inputImage)
      .minSimilarity(minSimilarity)
      .testMode(testMode)
      .build();

    Request request = prepareFormParamsRequest(requestObject);
    Response response = request.baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .post(MagnifAiEndPoints.FLEXIBLE_IMAGE_COMPARISON.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected MagnifAi Flex Image comparison StatusCode");

    return response.getResponse().as(FlexibleImageComparisonResponse.class);
  }

  /**
   * This method search for a child image within a parent image.
   *
   * @param testName      The name of the test associated with the image search.
   * @param executionId   A unique identifier for the execution context of the search.
   * @param parentImage   The file system path to the parent image within which the
   *                      search is performed.
   * @param childImage    The file system path to the child image that is being searched for
   *                      within the parent image.
   * @param minSimilarity The minimum similarity percentage required for a match between the
   *                      child and an area
   *                      of the parent image.
   * @param testMode      The mode of the test that may alter the search behavior or criteria.
   * @return A SearchImageResponse object containing the results of the image search.
   */
  @Step("Search Image Using execution ID: {1} And the test name: {0}")
  public static SearchImageResponse searchImage(String testName, String executionId,
                                                File parentImage, File childImage,
                                                String minSimilarity, String testMode) {

    SearchImageRequest requestObject = SearchImageRequest.builder()
      .testName(testName)
      .executionId(executionId)
      .parentImage(parentImage)
      .childImage(childImage)
      .minSimilarity(minSimilarity)
      .testMode(testMode)
      .build();

    Request request = prepareFormParamsRequest(requestObject);
    Response response = request.baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .post(MagnifAiEndPoints.SEARCH_IMAGE.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected MagnifAi search image StatusCode");

    return response.getResponse().as(SearchImageResponse.class);
  }

  /**
   * This method perform a flexible search for a specified child image within a parent image.
   *
   * @param testName    The name of the test associated with the image search.
   * @param executionId A unique identifier for the execution context of the search.
   * @param parentImage The file system path to the parent image within which the search is to
   *                    be performed.
   * @param childImage  The file system path to the child image that is being searched for within
   *                    the parent image.
   * @param testMode    The mode of the test that may alter the search behavior or criteria.
   * @return A FlexibleSearchResponse object containing the results of the flexible image search.
   */
  @Step("Flex Search Image Using execution ID: {1} And the test name: {0}")
  public static FlexibleSearchResponse flexibleSearchImage(String testName, String executionId,
                                                           File parentImage, File childImage,
                                                           String testMode) {

    FlexibleSearchImageRequest requestObject = FlexibleSearchImageRequest.builder()
      .testName(testName)
      .executionId(executionId)
      .parentImage(parentImage)
      .childImage(childImage)
      .testMode(testMode)
      .build();


    Request request = prepareFormParamsRequest(requestObject);
    Response response = request.baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .post(MagnifAiEndPoints.FLEXIBLE_SEARCH_IMAGE.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected MagnifAi Flex search Image StatusCode");

    return response.getResponse().as(FlexibleSearchResponse.class);
  }

  /**
   * This method locates the position of a main image and a relative image within a container image.
   *
   * @param testName       The name of the test associated with the image location.
   * @param executionId    A unique identifier for the execution context of the image location.
   * @param containerImage The file system path to the container image where the main and relative
   *                       images
   *                       are to be located.
   * @param mainImage      The file system path to the main image whose position is to be found
   *                       within
   *                       the container image.
   * @param relativeImage  The file system path to the relative image whose position is to be found
   *                       in relation
   *                       to the main image.
   * @param minSimilarity  The minimum similarity percentage required for a match when locating
   *                       the images.
   * @param testMode       The mode of the test that may alter the location behavior or criteria.
   * @return A LocateImageResponse object containing the results of the image location operation.
   */
  @Step("Locate Image Using execution ID: {1} And the test name: {0}")
  public static LocateImageResponse locateImage(String testName, String executionId,
                                                File containerImage,
                                                File mainImage, File relativeImage,
                                                String minSimilarity,
                                                String testMode) {

    LocateImageRequest requestObject = LocateImageRequest.builder()
      .testName(testName)
      .executionId(executionId)
      .containerImage(containerImage)
      .mainImage(mainImage)
      .relativeImage(relativeImage)
      .minSimilarity(minSimilarity)
      .testMode(testMode)
      .build();

    Request request = prepareFormParamsRequest(requestObject);
    Response response = request.baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .post(MagnifAiEndPoints.LOCATE_IMAGE.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected MagnifAi locate image StatusCode");

    return response.getResponse().as(LocateImageResponse.class);
  }

  /**
   * This method locates the position of a main image and a relative image within a container
   * image in a flexible way
   *
   * @param testName       The name of the test associated with the image location.
   * @param executionId    A unique identifier for the execution context of the image location.
   * @param containerImage The file system path to the container image where the main and relative
   *                       images are to be located.
   * @param mainImage      The file system path to the main image whose position is to be found
   *                       within
   *                       the container image.
   * @param relativeImage  The file system path to the relative image whose position is to be found
   *                       in relation to
   *                       the main image.
   * @param testMode       The mode of the test that may alter the location behavior or criteria.
   *                       This can include different levels of strictness or search algorithms.
   * @return FlexibleLocateImageResponse object containing the results of the flexible image.
   */
  @Step("Flex Locate Image Using execution ID: {1} And the test name: {0}")
  public static FlexibleLocateImageResponse flexibleLocateImage(String testName, String executionId,
                                                                File containerImage, File mainImage,
                                                                File relativeImage,
                                                                String testMode) {

    FlexibleLocateImageRequest requestObject = FlexibleLocateImageRequest.builder()
      .testName(testName)
      .executionId(executionId)
      .containerImage(containerImage)
      .mainImage(mainImage)
      .relativeImage(relativeImage)
      .testMode(testMode)
      .build();

    Request request = prepareFormParamsRequest(requestObject);
    Response response = request.baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .post(MagnifAiEndPoints.FLEXIBLE_LOCATE_IMAGE.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected MagnifAi Flex Locate Image StatusCode");

    return response.getResponse().as(FlexibleLocateImageResponse.class);
  }

  /**
   * This method converts the provided request object into a map of form parameters.
   *
   * @param requestObject The object to be converted into a map of form parameters.
   * @return A Request object with the form parameters and files set.
   */
  private static Request prepareFormParamsRequest(Object requestObject) {
    Request request = new Request();
    request.header(MagnifAiStaticParams.CONNECTION.getText(),
      MagnifAiStaticParams.KEEP_ALIVE.getText());
    HashMap<String, Object> bodyRequest = objectMapper.convertValue(requestObject, HashMap.class);
    HashMap<String, Object> clonedMap = new HashMap<>(bodyRequest);

    boolean hasFiles = false;
    for (Map.Entry<String, Object> entry : bodyRequest.entrySet()) {
      if (entry.getValue() != null && Files.exists(Paths.get(entry.getValue().toString()))) {
        request.multiPart(entry.getKey(), new File(entry.getValue().toString()),
          MagnifAiStaticParams.IMG_PNG.getText());
        clonedMap.remove(entry.getKey());
        hasFiles = true;
      }
    }
    if (hasFiles) {
      request.contentType(ContentType.MULTIPART);
    } else {
      request.contentType(ContentType.URLENC);
    }
    return request.formParams(clonedMap);
  }

  /**
   * This method serializes the given request object into a JSON string and sets it as the body
   * of the request.
   *
   * @param requestObject The object to be serialized into JSON.
   * @return A Request object with the JSON body set.
   * @throws RuntimeException if serialization of the request object into JSON fails.
   */
  private static Request prepareJsonRequest(Object requestObject) {
    Request request = new Request();
    request.header(MagnifAiStaticParams.CONNECTION.getText(),
      MagnifAiStaticParams.KEEP_ALIVE.getText());
    request.contentType(ContentType.JSON);
    try {
      String bodyRequest = objectMapper.writeValueAsString(requestObject);
      return request.body(bodyRequest);
    } catch (JsonProcessingException e) {
      throw new CustomException("Error parsing the Json object.", e);
    }
  }

  /**
   * Retrieves the asset as a byte array from the MagnifAi service using the specified asset ID.
   *
   * @param assetId The ID of the asset to retrieve.
   * @return A byte array containing the data of the asset.
   * @throws AssertionError If the response status code is not SC_OK (200),
   *                        indicating that the asset could not be retrieved successfully.
   */
  public static byte[] getAsset(String assetId) {
    Response executionResult = new Request()
      .baseUri(BASE_URL)
      .bearerAuth(refreshTokenIfNeeded())
      .contentType(ContentType.URLENC)
      .get(ASSETS.getText() + assetId);

    Assert.assertEquals(executionResult.getResponse().statusCode(), SC_OK,
      "Unexpected MagnifAi Get Asset StatusCode");

    return executionResult.getResponse().getBody().asByteArray();
  }
}
