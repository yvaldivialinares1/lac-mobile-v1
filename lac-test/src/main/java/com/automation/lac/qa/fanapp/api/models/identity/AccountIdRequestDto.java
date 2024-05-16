package com.automation.lac.qa.fanapp.api.models.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountIdRequestDto {
  private String email;
  private String password;
  private String rememberMe;
  private String hostOs;
}
