package com.automation.lac.qa.staffapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountPhotoResponseDto {
  private String id;
  private String ticketingId;
  private String memberType;
  private String email;
  private String firstname;
  private String lastname;
  private String chosenName;
  private long dateOfBirth;
  private String phone;
  private String accountType;
  private List<String> accountStatus;
  private boolean ageVerified;
  private String photoUrl;
  private long biometricDate;
}
