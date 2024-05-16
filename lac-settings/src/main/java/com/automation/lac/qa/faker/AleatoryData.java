package com.automation.lac.qa.faker;

import static com.automation.lac.qa.onlinesim.OnlineSimApi.getAvailableNumbersByCountry;
import static com.automation.lac.qa.onlinesim.OnlineSimApi.sanitizeNumber;
import static com.automation.lac.qa.onlinesim.models.OnlineSimResponse.NumberInfo;
import static java.lang.Boolean.FALSE;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.faker.enums.VehicleModel;
import com.automation.lac.qa.faker.models.MiniAccountInfo;
import com.automation.lac.qa.faker.models.PaymentMethod;
import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.VehicleInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.faker.models.userinfo.AddressInfo;
import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.faker.models.userinfo.PhoneInfo;
import com.automation.lac.qa.onlinesim.enums.OnlineSimAvailableCountry;
import com.automation.lac.qa.utils.Toolbox;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;

@UtilityClass
public class AleatoryData {

  private static final Faker FAKER = new Faker();
  private static final VehicleModel[] MAKES = VehicleModel.values();
  private static String firstName;
  private static String lastname;
  private final Random random = new Random();

  /**
   * Creates a random PersonalInfo object.
   *
   * @param type The type of fan which determines the age range for the birthdate.
   * @return A PersonalInfo object populated with random data.
   */
  public static UserInfo createRandomInfo(FanType type) {
    List<NumberInfo> numberInfoList = getAvailableNumbersByCountry(getAvailableCountries());
    NumberInfo numberInfo = numberInfoList.get(random.nextInt(numberInfoList.size()));

    UserInfo.UserInfoBuilder builder = UserInfo.builder()
      .personalInfo(buildPersonalInfo(type, numberInfo))
      .addressInfo(buildAddressInfo())
      .accountInfo(buildAccountInfo())
      .phoneInfo(buildPhoneInfo(numberInfo))
      .billingAddress(buildAddressInfo());

    return builder.build();
  }

  /**
   * Generates a random VehicleInfo object.
   *
   * @return A list of VehicleInfo object populated with random data.
   */
  public static List<VehicleInfo> createRandomVehicles(int numberOfVehicles) {

    List<VehicleInfo> vehicles = new ArrayList<>();

    for (int i = 0; i < numberOfVehicles; i++) {
      VehicleModel makeModel = MAKES[FAKER.number().numberBetween(0, MAKES.length)];
      List<String> modelList = makeModel.getModels();
      String model = modelList.get(FAKER.number().numberBetween(0, modelList.size()));
      String color = FAKER.color().name();

      VehicleInfo vehicle = VehicleInfo.builder()
        .vehicleNickname(String.format("%s%s", color, model))
        .licensePlateNumber(FAKER.regexify("[A-Z0-9]{7}"))
        .licensePlateState(FAKER.address().state())
        .make(makeModel.name())
        .model(model)
        .color(color)
        .isElectric(FAKER.bool().bool())
        .used(FALSE)
        .build();

      vehicles.add(vehicle);
    }
    return vehicles;
  }

  /**
   * Generates a random MiniAccountInfo object.
   *
   * @return A list of MiniAccountInfo object populated with random data.
   */
  public static List<MiniAccountInfo> createRandomMiniAccounts(int numberOfMiniAccount) {

    List<MiniAccountInfo> miniAccounts = new ArrayList<>();

    for (int i = 0; i < numberOfMiniAccount; i++) {
      String firstName = FAKER.name().firstName();
      String lastname = FAKER.name().lastName();
      LocalDate birthDate =
        FAKER.date().birthday(FanType.MINI.getMinAge(), FanType.MINI.getMaxAge())
          .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      String chosenName = FAKER.basketball().players();

      MiniAccountInfo miniAccount = MiniAccountInfo.builder()
        .firstName(firstName)
        .lastName(lastname)
        .birthDate(birthDate)
        .chosenName(chosenName)
        .used(FALSE)
        .build();

      miniAccounts.add(miniAccount);
    }

    return miniAccounts;
  }

  /**
   * Creates a random PaymentMethod object.
   * object populated with random data including a credit card number and expiration date.
   *
   * @return PaymentMethod
   */
  public static PaymentMethod createPaymentInfo() {
    int currentYear = LocalDate.now().getYear();
    int expiryYear = FAKER.number().numberBetween(currentYear, currentYear + 6);
    int expiryMonth = FAKER.number().numberBetween(1, 12);
    String formattedExpiryDate = String.format("%02d/%02d", expiryMonth, expiryYear % 100);

    return PaymentMethod.builder()
      .cardholderName(firstName + " " + lastname)
      .securityCode(FAKER.numerify("####"))
      .cardNumber(FAKER.finance().creditCard())
      .expirationDate(formattedExpiryDate)
      .build();
  }

  /**
   * Get a list of countries that have worked to use Phone OTP
   * @return List of countries
   */
  public static List<String> getAvailableCountries() {
    return Arrays.stream(OnlineSimAvailableCountry.values())
      .map(OnlineSimAvailableCountry::name).map(String::toLowerCase).toList();
  }

  private static PersonalInfo buildPersonalInfo(FanType type, NumberInfo number) {
    firstName = FAKER.name().firstName();
    lastname = FAKER.name().lastName();
    LocalDate birthDate = FAKER.date().birthday(type.getMinAge(), type.getMaxAge()).toInstant()
      .atZone(ZoneId.systemDefault()).toLocalDate();
    String countryCode = "US";

    return PersonalInfo.builder()
      .firstName(firstName)
      .lastName(lastname)
      .birthDate(birthDate)
      .phoneNumber(sanitizeNumber(String.valueOf(number.getCountry()), number.getFullNumber()))
      .countryCode(countryCode)
      .phoneOtpCountry(number.getCountryName())
      .build();
  }

  private static AddressInfo buildAddressInfo() {
    String country = "United States";
    String randomState = FAKER.address().state();

    return AddressInfo.builder()
      .country(country)
      .state(randomState)
      .city(FAKER.address().city())
      .primaryAddress(FAKER.address().streetAddress())
      .secondaryAddress(FAKER.address().secondaryAddress().replace(".", ""))
      .zipCode("20038")
      .build();
  }

  private static AccountInfo buildAccountInfo() {
    String email = Toolbox.generateValidEmail();
    String password = Toolbox.generatePassword(10, 20, true, true, true, true);

    return AccountInfo.builder()
      .email(email)
      .emailLocalPart(email.substring(0, email.indexOf('@')))
      .password(password)
      .confirmPassword(password)
      .build();
  }

  private static PhoneInfo buildPhoneInfo(NumberInfo number) {
    String senderCode =
      OnlineSimAvailableCountry.valueOf(number.getCountryName().toUpperCase()).getSenderCode();

    return PhoneInfo.builder()
      .phoneOtpCountry(number.getCountryName())
      .phoneOtpCountryCode(String.valueOf(number.getCountry()))
      .phoneOtpSenderCode(senderCode)
      .build();
  }
}
