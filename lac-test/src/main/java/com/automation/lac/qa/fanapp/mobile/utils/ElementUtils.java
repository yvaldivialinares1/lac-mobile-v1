package com.automation.lac.qa.fanapp.mobile.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebElement;

@UtilityClass
public class ElementUtils {
  public static boolean isElementChecked(WebElement element) {
    return "true".equals(element.getAttribute("checked"));
  }
}