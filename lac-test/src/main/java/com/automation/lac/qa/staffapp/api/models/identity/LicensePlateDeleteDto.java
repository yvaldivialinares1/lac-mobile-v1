package com.automation.lac.qa.staffapp.api.models.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicensePlateDeleteDto {
  private String id;
  private String licensePlate;
  private String state;
  private String accountId;
  private String nickname;
  private String make;
  private String model;
  private String color;
  private int createdDate;
  private int updatedDate;
  private String memberType;
}
