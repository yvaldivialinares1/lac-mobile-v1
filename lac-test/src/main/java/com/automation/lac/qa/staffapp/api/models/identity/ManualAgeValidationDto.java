package com.automation.lac.qa.staffapp.api.models.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualAgeValidationDto {
  private String intuitDomeAccountId;
  private boolean ageVerified;
  private long ageVerificationExpirationDate;
}
