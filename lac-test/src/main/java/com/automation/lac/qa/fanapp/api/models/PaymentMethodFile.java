package com.automation.lac.qa.fanapp.api.models;

import static com.automation.lac.qa.fanapp.api.enums.FilesPath.PAYMENT_METHOD_PATH;

import com.automation.lac.qa.utils.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * Represents a credit card with details such as the brand, card number, name, etc.
 */
@Data
public class PaymentMethodFile {

  private static final SecureRandom random = new SecureRandom();
  private List<PaymentMethod> paymentMethods;

  @Data
  public static class PaymentMethod {
    private String brand;
    private long cardNumber;
    private String name;
    private int securityCode;
    private String expirationDate;
    private String type;
    private String nickname;
    private boolean preferred;
    private boolean used;
    private boolean active;
  }

  /**
   * Reads the credit cards file and constructs a PaymentMethodFile instance from the JSON data.
   *
   * @return A PaymentMethodFile instance containing lists of active and inactive credit cards.
   * @throws RuntimeException if there is an error reading or deserializing the JSON file.
   */
  private static PaymentMethodFile fromJsonFile() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      String jsonContent = new String(Files.readAllBytes(Paths.get(PAYMENT_METHOD_PATH.getText())));
      List<PaymentMethod> paymentMethodList = mapper.readValue(jsonContent,
        new TypeReference<List<PaymentMethod>>() {
        });
      PaymentMethodFile paymentMethodFile = new PaymentMethodFile();
      paymentMethodFile.setPaymentMethods(paymentMethodList);
      return paymentMethodFile;
    } catch (IOException e) {
      throw new CustomException("Error reading or deserializing the JSON file.", e);
    }
  }

  /**
   * Retrieves a list of valid active payment method that have not been used from the credit cards
   * file.
   *
   * @return A List of active and not used PaymentMethod objects from the credit cards file.
   * @throws RuntimeException If there is an error reading or deserializing the JSON file.
   */
  public static List<PaymentMethod> getActiveAndNotUsedPaymentMethods() {
    PaymentMethodFile paymentMethodFile = fromJsonFile();
    return paymentMethodFile.getPaymentMethods().stream()
      .filter(paymentMethod -> paymentMethod.isActive() && !paymentMethod.isUsed())
      .collect(Collectors.toList());
  }
}
