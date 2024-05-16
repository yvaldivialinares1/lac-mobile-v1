package com.automation.lac.qa.xray;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A Custom Annotation to inject additional information into a TestNG Test
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Xray {

  /**
   * Test attribute
   *
   * @return test
   */
  String[] test() default {};

}