package com.automation.lac.qa.staffapp.api.models.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewIntuitDomeAccountDto {
  private String email;
  private String password;
  private String accessToken;
  private String refreshToken;
  private String firstname;
  private String lastname;
  private String chosenName;
  private String dateOfBirth;
  private String countryPrefix;
  private String phone;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String country;
  private String zipCode;
  private String hostOs;
}
