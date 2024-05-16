package com.automation.lac.qa.fanapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountIdResponse {
  private ShortLoginRegistrationIntuitDomeAccountDto intuitDomeAccount;
  private boolean isIntuitDomeAccount;
  private boolean doesAccountExist;
  private String ticketMasterIdToken;
  private String accessToken;
  private String refreshToken;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public class ShortLoginRegistrationIntuitDomeAccountDto {
    private String memberType;
    private String id;
    private String ticketingId;
    private String email;
    private String firstname;
    private String lastname;
    private String chosenName;
    private long dateOfBirth;
    private long createdDate;
    private long updatedDate;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String accountType;
  }
}
