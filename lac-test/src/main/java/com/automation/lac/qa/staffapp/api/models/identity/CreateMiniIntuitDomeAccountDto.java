package com.automation.lac.qa.staffapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMiniIntuitDomeAccountDto {
  private String intuitDomeAccountId;
  @JsonProperty("firstname")
  private String firstName;
  @JsonProperty("lastname")
  private String lastName;
  private String chosenName;
  private long dateOfBirth;
}
