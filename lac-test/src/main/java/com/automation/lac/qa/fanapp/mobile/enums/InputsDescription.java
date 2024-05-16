package com.automation.lac.qa.fanapp.mobile.enums;

import lombok.Getter;

@Getter
public enum InputsDescription {
  ADDRESS("Address Line One Input"),
  APP_FLOOR("Input/App/Floor Input"),
  CARD_NUMBER("Card number"),
  CITY("City Input"),
  COLOR("Color"),
  COUNTRY("Country"),
  COUNTRY_CODE("Country Code"),
  CREATE_INTUIT_DOME_ACCOUNT("Create Intuit Dome Account"),
  DAY("Day Input"),
  EMAIL_ADDRESS("Email Address"),
  EXPIRY_DATE("Expiry date"),
  FIRST_NAME("First Name Input"),
  LAST_NAME("Last Name Input"),
  LICENSE_PLATE("License Plate"),
  MAKE("Make"),
  MODEL("Model"),
  MONTH("Set month"),
  NAME_ON_CARD("Name on card"),
  NICKNAME("nickname edit-box."),
  OTP_CODE("OTP code"),
  PASSWORD("password"),
  PHONE_NUMBER("Phone Number Input"),
  SEARCH("Search"),
  SECURITY_CODE("Security code"),
  STATE("State Input"),
  TICKET_SEARCH("Ticket search textBox"),
  US("United States"),
  USER_NAME("username"),
  VEHICLE_NICKNAME("Vehicle nickname"),
  YEAR("Set year"),
  ZIP_CODE("Zip Code Input");

  private final String value;

  InputsDescription(String value) {
    this.value = value;
  }
}
