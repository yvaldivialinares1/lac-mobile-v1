package com.automation.lac.qa.staffapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicensePlateRelationship {
  private String id;
  private String licensePlate;
  private String state;
  private String accountId;
  private String nickname;
  private String make;
  private String model;
  private String color;
  private long createdDate;
  private long updatedDate;
}
