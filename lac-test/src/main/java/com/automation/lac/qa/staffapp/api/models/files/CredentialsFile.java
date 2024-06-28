package com.automation.lac.qa.staffapp.api.models.files;

import static com.automation.lac.qa.staffapp.api.enums.FilesPath.STAFF_CREDENTIALS_PATH;

import com.automation.lac.qa.utils.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import lombok.Data;

@Data
public class CredentialsFile {

  private static final SecureRandom random = new SecureRandom();
  private List<StaffAccount> accounts;

  @Data
  public static class StaffAccount {
    private String type;
    private String mail;
    private String password;
    private boolean enabled;
  }

  /**
   * Reads and deserializes a JSON file containing credentials.
   *
   * @return An instance of {@link CredentialsFile} with the credentials.
   * @throws CustomException If an error occurs during reading or deserialization.
   */
  private static CredentialsFile readCredentialsFile() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      String jsonContent =
        new String(Files.readAllBytes(Paths.get(STAFF_CREDENTIALS_PATH.getText())));
      return mapper.readValue(jsonContent, CredentialsFile.class);
    } catch (IOException e) {
      throw new CustomException("Error reading or deserializing the JSON file.", e);
    }
  }

  /**
   * Retrieves a valid enabled account of the specified type from the credentials file.
   *
   * @param type The type of account to retrieve.
   * @return A randomly enabled Account object of the specified type from the credentials file.
   * @throws CustomException If there is an error reading or deserializing the JSON file,
   *                         or if no enabled accounts of the specified type are available.
   */
  public static StaffAccount getStaffValidCredentials(String type) {
    CredentialsFile credentialsFile = readCredentialsFile();
    List<StaffAccount> enabledAccountsByType = credentialsFile.getAccounts().stream()
      .filter(account -> account.isEnabled() && type.equals(account.getType()))
      .toList();

    if (enabledAccountsByType.isEmpty()) {
      throw new CustomException("There are no enabled accounts available for the type: " + type);
    }

    return enabledAccountsByType.get(random.nextInt(enabledAccountsByType.size()));
  }
}
