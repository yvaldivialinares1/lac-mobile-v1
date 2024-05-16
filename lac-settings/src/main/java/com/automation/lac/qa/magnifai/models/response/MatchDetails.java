package com.automation.lac.qa.magnifai.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDetails {
  private Integer topLeftX;
  private Integer topLeftY;
  private Integer bottomRightX;
  private Integer bottomRightY;
}