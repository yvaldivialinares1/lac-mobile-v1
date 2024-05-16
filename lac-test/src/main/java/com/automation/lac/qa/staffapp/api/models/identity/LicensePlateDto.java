package com.automation.lac.qa.staffapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class LicensePlateDto {
  private String licensePlate;
  private String state;
  private String id;
  private String intuitDomeAccountId;
  private String nickname;
  private String make;
  private String model;
  private String color;
}
