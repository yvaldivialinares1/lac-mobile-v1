package com.automation.lac.qa.usercreation.models;

import com.automation.lac.qa.faker.enums.FanType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class provides getters and setters for adding and getting the user creation result by Fan
 * Type.
 */
@Data
@Builder
@AllArgsConstructor
public class UserCreationResultByFanType {

  private FanType fanType;
  private List<UserCreationResult> userCreationResultList;
}