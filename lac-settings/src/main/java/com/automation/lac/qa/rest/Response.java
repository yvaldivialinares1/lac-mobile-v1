package com.automation.lac.qa.rest;

import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hamcrest.Matcher;
import org.w3c.dom.Node;


@Data
@AllArgsConstructor
public class Response {

  private io.restassured.response.Response rresponse;

  public String getValueFromKey(String key) {
    return getValidatableResponse().extract().path(key).toString();
  }

  public ResponseBody getBody() {
    return this.rresponse.body();
  }

  public io.restassured.response.Response getResponse() {
    return this.rresponse;
  }

  /**
   * body(greeting.first, equalTo("Mola"))
   * @param path  String
   * @param matcher Matcher
   * @return Response
   */
  public <T> Response body(String path, org.hamcrest.Matcher<T> matcher) {
    this.rresponse.then().body(path, matcher);
    return this;
  }

  public <T> Response body(org.hamcrest.Matcher<T> matcher) {
    this.rresponse.then().body("", matcher);
    return this;
  }


  /**
   * body validations
   * body(hasXPath(), equalTo("Mola"))
   * @param node df
   * @param matcher bdf
   * @return dfg
   */
  public <T> Response body(Matcher<Node> node, org.hamcrest.Matcher<T> matcher) {
    this.rresponse.then().body(node, matcher);
    return this;
  }

  /**
   * status validations
   * @param matcher bdf
   * @return dfg
   */
  public Response status(org.hamcrest.Matcher<Integer> matcher) {
    this.rresponse.then().statusCode(matcher);
    return this;
  }

  public ValidatableResponse getValidatableResponse() {
    return this.rresponse.then();
  }

}