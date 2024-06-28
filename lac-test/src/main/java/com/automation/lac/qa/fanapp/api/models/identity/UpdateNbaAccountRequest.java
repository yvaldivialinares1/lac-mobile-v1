package com.automation.lac.qa.fanapp.api.models.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNbaAccountRequest {
  private PrimaryPhone primaryPhone;
  private BillingAddress billingAddress;
  private ExtendedNameAttributes extendedNameAttributes;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class PrimaryPhone {
    private String countryPrefix;
    private String extension;
    private String number;
    private String phoneDeliveryPermission;
    private String type;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class BillingAddress {
    private String state;
    private String city;
    private String addressLine1;
    private String addressLine2;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ExtendedNameAttributes {
    private String preferredName;
  }
}

