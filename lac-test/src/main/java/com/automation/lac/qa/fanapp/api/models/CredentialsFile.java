package com.automation.lac.qa.fanapp.api.models;

import static com.automation.lac.qa.fanapp.api.enums.FilesPath.CREDENTIALS_PATH;

import com.automation.lac.qa.utils.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import lombok.Data;

/**
 * Class representing the credentials file structure with active and inactive accounts.
 */
@Data
public class CredentialsFile {

  private static final Random random = new Random();
  private List<Account> accounts;

  @Data
  public static class Account {
    private String type;
    private String mail;
    private String password;
    private boolean enabled;
  }

  /**
   * Reads the credentials file and constructs a CredentialsFile instance from the JSON data.
   *
   * @return A CredentialsFile instance containing lists of active and inactive accounts.
   * @throws RuntimeException if there is an error reading or deserializing the JSON file.
   */
  private static CredentialsFile fromJsonFile() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      String jsonContent = new String(Files.readAllBytes(Paths.get(CREDENTIALS_PATH.getText())));
      return mapper.readValue(jsonContent, CredentialsFile.class);
    } catch (IOException e) {
      throw new CustomException("Error reading or deserializing the JSON file: ", e);
    }
  }

  /**
   * Retrieves a valid enabled account from the credentials file.
   *
   * @return A randomly selected enabled Account object from the credentials file.
   * @throws RuntimeException If there is an error reading or deserializing the JSON file,
   *                          or if no enabled accounts are available.
   */
  public static Account getValidCredentials() {
    CredentialsFile credentialsFile = fromJsonFile();
    List<Account> enabledAccounts = credentialsFile.getAccounts().stream()
      .filter(Account::isEnabled)
      .toList();

    if (enabledAccounts.isEmpty()) {
      throw new CustomException("There are no enabled accounts available.");
    }

    return enabledAccounts.get(random.nextInt(enabledAccounts.size()));
  }
}
