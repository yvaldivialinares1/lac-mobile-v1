package com.automation.lac.qa.magnifai.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlexLocateDetails {
  private String containerImageRef;
  private String mainImageRef;
  private String relativeImageRef;
  private String resultImageRef;
  private RegionDetails mainRegion;
  private RegionDetails relativeRegion;
  private String relativePositionFromMain;
  private String description;
}