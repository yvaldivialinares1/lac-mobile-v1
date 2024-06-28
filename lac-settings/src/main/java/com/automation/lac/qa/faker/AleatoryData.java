package com.automation.lac.qa.faker;

import static com.automation.lac.qa.faker.enums.FanType.ADULT;
import static com.automation.lac.qa.faker.enums.FanType.MINI;
import static com.automation.lac.qa.faker.enums.FanType.YOUNG_ADULT;
import static com.automation.lac.qa.onlinesim.OnlineSimApi.getAvailableNumbersByCountry;
import static com.automation.lac.qa.onlinesim.OnlineSimApi.sanitizeNumber;
import static com.automation.lac.qa.onlinesim.models.OnlineSimResponse.NumberInfo;
import static com.automation.lac.qa.utils.UserCreationConstant.TEAMMATE;
import static com.automation.lac.qa.utils.UserCreationConstant.VEHICLE;
import static java.lang.Boolean.FALSE;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.faker.enums.VehicleModel;
import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.faker.models.userinfo.AddressInfo;
import com.automation.lac.qa.faker.models.userinfo.PaymentMethod;
import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.faker.models.userinfo.PhoneInfo;
import com.automation.lac.qa.faker.models.userinfo.TeammateInfo;
import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import com.automation.lac.qa.onlinesim.enums.OnlineSimAvailableCountry;
import com.automation.lac.qa.utils.Toolbox;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;

@UtilityClass
public class AleatoryData {

  private static final Faker FAKER = new Faker();
  private static final VehicleModel[] MAKES = VehicleModel.values();
  private static String firstName;
  private static String lastname;
  private final SecureRandom random = new SecureRandom();

  /**
   * Creates a random PersonalInfo list of objects.
   *
   * @param usersNumber The numbers of users to create information.
   * @return A PersonalInfo list of objects populated with random data.
   */
  public static List<UserInfo> createRandomInfoList(int usersNumber, FanType fanType) {
    List<UserInfo> usersInfoList = new ArrayList<>();

    for (int i = 0; i < usersNumber; i++) {
      usersInfoList.add(createRandomInfo(fanType));
    }

    return usersInfoList;
  }

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

    if (!TEAMMATE.isEmpty() && type.equals(ADULT) || type.equals(YOUNG_ADULT))
      builder.teammatesInfo(createRandomTeammates(TEAMMATE));

    if (VEHICLE > 0 && type != MINI)
      builder.vehiclesInfo(createRandomVehicles(VEHICLE));

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
   * Generates a random TeammateInfo object.
   *
   * @return A list of TeammateInfo object populated with random data.
   */
  public static List<TeammateInfo> createRandomTeammates(int numberOfMiniAccount) {
    List<Integer> teammateAges = new ArrayList<>();

    for (int i = 0; i < numberOfMiniAccount; i++) {
      teammateAges.add(FAKER.random().nextInt(FanType.MINI.getMinAge(), FanType.MINI.getMaxAge()));
    }

    return createRandomTeammates(teammateAges);
  }

  /**
   * Generates a random TeammateInfo object.
   *
   * @return A list of TeammateInfo object populated with random data.
   */
  public static List<TeammateInfo> createRandomTeammates(List<Integer> teammateAges) {

    List<TeammateInfo> miniAccounts = new ArrayList<>();

    for (int teammateAge : teammateAges) {
      String firstName = FAKER.name().firstName();
      String lastname = FAKER.name().lastName();
      LocalDate birthDate = getBirthDayDate(teammateAge);
      String chosenName = FAKER.basketball().players();

      TeammateInfo miniAccount = TeammateInfo.builder()
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
   *
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
    String password = "Test@Clippers2024";

    return AccountInfo.builder()
      .email(email)
      .emailLocalPart(email.substring(0, email.indexOf('@')))
      .password(password)
      .confirmPassword(password)
      .build();
  }

  private static PhoneInfo buildPhoneInfo(NumberInfo number) {
    return PhoneInfo.builder()
      .phoneOtpCountry(number.getCountryName())
      .phoneOtpCountryCode(String.valueOf(number.getCountry()))
      .build();
  }

  private static LocalDate getBirthDayDate(Integer age) {
    return FAKER.date().birthday(age, age)
      .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}
