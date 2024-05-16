package com.automation.lac.qa.staffapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedAccount {
  private String id;
  private String email;
  private String firstname;
  private String lastname;
  private String chosenName;
  private long dateOfBirth;
  private String phone;
  private String accountType;
}
