package com.automation.lac.qa.faker.models;

import com.automation.lac.qa.faker.enums.FanType;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Groups a list of UserInfo by Fan
 */
@Data
@Builder
public class UserInfoByFanType {

  private FanType fanType;
  private List<UserInfo> usersInfo;
}