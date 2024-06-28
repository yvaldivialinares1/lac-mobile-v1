package com.automation.lac.qa.fanapp.api.models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubListResponse {
  private List<Club> clubs;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class Club {
    private int clubId;
    private String clubName;
    private List<CmsData> cmsData;
  }


  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class CmsData {
    private int clubId;
    private String clubName;
  }
}
