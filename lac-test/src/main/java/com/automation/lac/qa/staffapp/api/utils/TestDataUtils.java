package com.automation.lac.qa.staffapp.api.utils;

import static com.automation.lac.qa.faker.enums.FanType.MINI;
import static java.lang.Boolean.FALSE;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.faker.enums.VehicleModel;
import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import com.automation.lac.qa.staffapp.api.models.identity.CreateMiniIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.NewIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.NfcRelationship;
import com.automation.lac.qa.utils.Toolbox;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;

@UtilityClass
public class TestDataUtils {

  private static final Faker FAKER = new Faker();
  private static final VehicleModel[] MAKES = VehicleModel.values();
  private static final String[] VEHICLE_MAKES = {"Toyota", "Honda", "Ford", "Chevrolet", "BMW"};
  private static final SecureRandom random = new SecureRandom();

  /**
   * Creates a random NewIntuitDomeAccountDto object.
   *
   * @param type The type of fan which determines the age range for the birthdate.
   * @return A NewIntuitDomeAccountDto object populated with random data.
   */
  public static NewIntuitDomeAccountDto createRandomNewIntuitDomeAccountDto(FanType type) {
    LocalDate birthDate = FAKER.date().birthday(type.getMinAge(), type.getMaxAge()).toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();

    String firstName = FAKER.name().firstName();
    String lastname = FAKER.name().lastName();
    String email = Toolbox.generateValidEmail();
    String password = Toolbox.generatePassword(10, 20, true,
      true, true, true);

    return NewIntuitDomeAccountDto.builder()
      .email(email)
      .password(password)
      .firstname(firstName)
      .lastname(lastname)
      .chosenName(lastname + " " + lastname)
      .dateOfBirth(birthDate.toString())
      .countryPrefix("+57")
      .phone("3208177752")
      .address1("2509 Rainbow Road line 1")
      .address2("4444 Rainbow Road line 2")
      .city("Dallas")
      .state("Texas")
      .country("US")
      .zipCode("20038")
      .hostOs("ANDROID")
      .build();
  }

  /**
   * Creates a random CreateMiniIntuitDomeAccountDto object.
   *
   * @param intuitDomeAccountId id of parent account.
   * @return A CreateMiniIntuitDomeAccountDto object populated with random data.
   */
  public static CreateMiniIntuitDomeAccountDto createRandomCreateMiniIntuitDomeAccountDto(
    String intuitDomeAccountId) {
    long birthDate = FAKER.date().birthday(MINI.getMinAge(), MINI.getMaxAge()).toInstant()
      .atZone(ZoneId.systemDefault())
      .toEpochSecond();

    return CreateMiniIntuitDomeAccountDto.builder()
      .intuitDomeAccountId(intuitDomeAccountId)
      .firstName(FAKER.name().firstName())
      .lastName(FAKER.name().lastName())
      .chosenName(FAKER.name().title())
      .dateOfBirth(birthDate)
      .build();
  }

  /**
   * Creates a random NfcRelationship object.
   *
   * @param accountId id of fan account.
   * @return A NfcRelationship object populated with random data.
   */
  public static NfcRelationship createRandomNfcRelationship(String accountId) {
    return NfcRelationship.builder()
      .id(FAKER.regexify("[a-zA-Z0-9]{12}"))
      .accountId(accountId)
      .memberType("FAN")
      .build();
  }

  /**
   * Generates a random VehicleInfo object.
   *
   * @param numberOfVehicles int indication the expected amount of vehicles generated
   * @return A list of VehicleInfo object populated with random data.
   */
  public static List<VehicleInfo> createRandomVehicles(int numberOfVehicles) {
    int randomIndex = random.nextInt(VEHICLE_MAKES.length);
    String make = VEHICLE_MAKES[randomIndex];
    List<VehicleInfo> vehicles = new ArrayList<>();

    for (int i = 0; i < numberOfVehicles; i++) {
      VehicleModel makeModel = MAKES[FAKER.number().numberBetween(0, MAKES.length)];
      List<String> modelList = makeModel.getModels();
      String model = modelList.get(FAKER.number().numberBetween(0, modelList.size()));

      VehicleInfo vehicle = VehicleInfo.builder()
        .vehicleNickname(make.substring(0, 3) + model.substring(0, 3))
        .licensePlateNumber(FAKER.regexify("[A-Z0-9]{7}"))
        .licensePlateState(FAKER.address().state())
        .make(make)
        .model(model)
        .color(FAKER.color().name())
        .used(FALSE)
        .build();

      vehicles.add(vehicle);
    }
    return vehicles;
  }
}
