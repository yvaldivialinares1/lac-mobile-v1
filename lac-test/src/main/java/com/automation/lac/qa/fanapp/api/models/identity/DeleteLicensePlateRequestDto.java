package com.automation.lac.qa.fanapp.api.models.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteLicensePlateRequestDto {
  private String id;
  private String licensePlate;
  private String state;
  private String accountId;
}