package com.automation.lac.qa.magnifai.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlexSearchDetails {
  private String parentImageRef;
  private String childImageRef;
  private String resultImageRef;
  private RegionDetails childRegion;
}