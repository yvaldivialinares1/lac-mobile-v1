package com.automation.lac.qa.fanapp.api.models;

import static com.automation.lac.qa.fanapp.api.enums.FilesPath.CREDENTIALS_PATH;

import com.automation.lac.qa.utils.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.security.SecureRandom;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class representing the credentials file structure with active and inactive accounts.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CredentialsFile extends AbstractJsonFileReader<CredentialsFile> {

  private static final SecureRandom random = new SecureRandom();
  private List<Account> accounts;

  @Override
  protected String getFilePath() {
    return CREDENTIALS_PATH.getText();
  }

  @Override
  protected TypeReference<CredentialsFile> getTypeReference() {
    return new TypeReference<CredentialsFile>() {};
  }

  @Data
  public static class Account {
    private String type;
    private String mail;
    private String password;
    private boolean enabled;
  }

  /**
   * Retrieves a valid enabled account from the credentials file.
   *
   * @return A randomly selected enabled Account object from the credentials file.
   * @throws CustomException If there is an error reading or deserializing the JSON file,
   *                         or if no enabled accounts are available.
   */
  public static Account getValidCredentials() {
    CredentialsFile credentialsFile = new CredentialsFile().fromJsonFile();
    List<Account> enabledAccounts = credentialsFile.getAccounts().stream()
      .filter(Account::isEnabled)
      .toList();

    if (enabledAccounts.isEmpty()) {
      throw new CustomException("There are no enabled accounts available.");
    }

    return enabledAccounts.get(random.nextInt(enabledAccounts.size()));
  }

  /**
   * Retrieves a valid enabled account of the specified type from the credentials file.
   *
   * @param type The type of account to retrieve.
   * @return A randomly enabled Account object of the specified type from the credentials file.
   * @throws CustomException If there is an error reading or deserializing the JSON file,
   *                         or if no enabled accounts of the specified type are available.
   */
  public static Account getValidCredentials(String type) {
    CredentialsFile credentialsFile = new CredentialsFile().fromJsonFile();
    List<Account> enabledAccountsByType = credentialsFile.getAccounts().stream()
      .filter(account -> account.isEnabled() && type.equals(account.getType()))
      .toList();

    if (enabledAccountsByType.isEmpty()) {
      throw new CustomException("There are no enabled accounts available for the type: " + type);
    }

    return enabledAccountsByType.get(random.nextInt(enabledAccountsByType.size()));
  }
}