package com.automation.lac.qa.faker.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * Enumerates vehicle makes with a list of associated models.
 * This enum provides a list of popular models for each vehicle make.
 */
@Getter
public enum VehicleModel {
  CHEVROLET(Arrays.asList("Equinox", "Malibu", "Silverado", "Tahoe", "Traverse")),
  FORD(Arrays.asList("Bronco", "Edge", "Escape", "Explorer", "Mustang")),
  HONDA(Arrays.asList("Accord", "Civic", "CRV", "HRV", "Pilot")),
  HYUNDAI(Arrays.asList("Elantra", "Kona", "Palisade", "Santa Fe", "Tucson")),
  JEEP(Arrays.asList("Grand Cherokee", "Wrangler", "Compass", "Gladiator", "Renegade")),
  KIA(Arrays.asList("Sportage", "Forte", "Telluride", "Sorento", "Soul")),
  NISSAN(Arrays.asList("Rogue", "Sentra", "Altima", "Frontier", "Kicks")),
  DODGE(Arrays.asList("Durango", "Challenger", "Charger", "Journey", "Hornet")),
  TESLA(Arrays.asList("Model3", "ModelY", "ModelX", "ModelS", "Cybertruck")),
  TOYOTA(Arrays.asList("Camry", "Corolla", "Highlander", "RAV4", "Tacoma"));

  private final List<String> models;

  /**
   * Constructs an instance of the enum with a list of models for the vehicle make.
   *
   * @param models The list of models associated with the vehicle make.
   */
  VehicleModel(List<String> models) {
    this.models = models;
  }

  private static final Map<String, VehicleModel> MAKE_MAP;

  static {
    Map<String, VehicleModel> map = new HashMap<>();
    for (VehicleModel makeModel : VehicleModel.values()) {
      map.put(makeModel.name(), makeModel);
    }
    MAKE_MAP = Collections.unmodifiableMap(map);
  }

  /**
   * Retrieves the VehicleModel enum constant by its make name.
   *
   * @param makeName The name of the vehicle make for which the models are required.
   * @return The VehicleModel enum constant associated with the given make name.
   */
  public static VehicleModel getMakeModelByName(String makeName) {
    return MAKE_MAP.get(makeName);
  }
}
