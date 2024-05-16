package com.automation.lac.qa.magnifai.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocateDetails {
  private String containerImageRef;
  private String mainImageRef;
  private String relativeImageRef;
  private String resultImageRef;
  private Integer minSimilarity;
  private RegionDetails mainRegion;
  private RegionDetails relativeRegion;
  private String relativePositionFromMain;
  private String description;
}