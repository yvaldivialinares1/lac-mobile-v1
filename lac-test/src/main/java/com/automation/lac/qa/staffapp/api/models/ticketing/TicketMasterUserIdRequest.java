package com.automation.lac.qa.staffapp.api.models.ticketing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketMasterUserIdRequest {
  private String email;
  private String firstname;
  private String lastname;
  private long dateOfBirth;
  private String phone;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String country;
  private String zipCode;
}
