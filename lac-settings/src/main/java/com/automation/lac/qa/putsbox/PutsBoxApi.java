package com.automation.lac.qa.putsbox;

import static com.automation.lac.qa.driver.AppiumConstants.NEW_COMMAND_TIMEOUT;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.putsbox.enums.PutsBoxEndPoints;
import com.automation.lac.qa.putsbox.models.response.EmailDetails;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.PropertiesManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

@UtilityClass
public class PutsBoxApi {

  private static final String BASE_URL =
    PropertiesManager.getParameter("framework.putsbox.baseurl");

  /**
   * Fluent wait instance for managing polling and timeouts during asynchronous operations.
   */
  private static final FluentWait<Request> WAIT = new FluentWait<>(new Request())
    .withTimeout(Duration.ofSeconds(NEW_COMMAND_TIMEOUT))
    .pollingEvery(Duration.ofSeconds(5))
    .ignoring(NoSuchElementException.class);

  private static final Pattern OTP_PATTERN = Pattern.compile("\\d{6}");
  private static final ObjectMapper MAPPER = new ObjectMapper();

  /**
   * Retrieves the OTP code from the latest email sent to the specified email address.
   * Polls the email service until an email arrives or the timeout is reached.
   *
   * @param email The email address to which the OTP was sent.
   * @return The six-digit OTP code from the email content.
   * @throws IllegalStateException if no email is received within the wait period,
   *                               if the email list is empty, or if the OTP code is not found.
   */
  @SneakyThrows
  public static String getOtpCodeFromEmails(String email) {
    Response result = WAIT.until(request -> {
      Response response = new Request()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON)
        .header("Accept", "application/json")
        .get("/" + email + PutsBoxEndPoints.GET_INSPECT.getText());

      Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
        "Unexpected PutsBox get inspect StatusCode");

      String responseBody = response.getResponse().asString();
      return "[]".equals(responseBody) ? null : response;
    });

    if (result == null) {
      throw new CustomException("No emails received within the wait period.");
    }

    List<EmailDetails> emailItems = MAPPER.readValue(result.getResponse().asString(),
      new TypeReference<List<EmailDetails>>() {
      });

    if (emailItems.isEmpty()) {
      throw new CustomException("Email list is empty.");
    }

    String emailContent = emailItems.get(0).getText();
    Matcher matcher = OTP_PATTERN.matcher(emailContent);
    if (matcher.find()) {
      return matcher.group();
    } else {
      throw new CustomException("OTP not found in the email content.");
    }
  }
}
