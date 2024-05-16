package com.automation.lac.qa.rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;

public class Request {

  protected RequestSpecification requestSpecification;
  private ConditionFactory await = null;
  private int expectedStatus = 200;
  
  /**
   * Requester for HTTP and SOAP protocols using RestAssured
   */
  public Request() {
    this.requestSpecification = RestAssured
      .given()
      .filter(new AllureRestAssured())
      .filter(new RequestLoggingFilter(LogDetail.ALL, false, System.out))
      .filter(new ResponseLoggingFilter(LogDetail.ALL, false, System.out))
      .relaxedHTTPSValidation()
      .redirects()
      .follow(false);
  }

  public Request(RequestSpecification specification) {
    this.requestSpecification = specification;
  }

  /**
   * Set encoding enabled
   *
   * @param encodingEnabled boolean indicating if request url must be encoded
   * @return Request
   */
  public Request urlEncodingEnabled(boolean encodingEnabled) {
    this.requestSpecification.urlEncodingEnabled(encodingEnabled);
    return this;
  }

  /**
   * Set number of retries and expected status on success request
   *
   * @param retries        number of retries
   * @param expectedStatus success request status
   * @return Request
   */
  public Request retries(int retries, int expectedStatus) {
    this.await = Awaitility
      .await()
      .pollInterval(5L, TimeUnit.SECONDS)
      .atMost(5L * retries, TimeUnit.SECONDS);
    this.expectedStatus = expectedStatus;
    return this;
  }

  /**
   * Base URI
   *
   * @param baseUri String
   * @return Request
   */
  public Request baseUri(String baseUri) {
    this.requestSpecification.baseUri(baseUri);
    return this;
  }

  /**
   * Basic HTTP auth with username and password
   *
   * @param username credential
   * @param password credential
   * @return Request
   */
  public Request basicAuth(String username, String password) {
    this.requestSpecification.auth().basic("username", username);
    this.requestSpecification.auth().basic("password", password);
    return this;
  }

  /**
   * Basic HTTP preemtive auth with username and password
   *
   * @param username credential
   * @param password credential
   * @return Request
   */
  public Request basicPreemptiveAuth(String username, String password) {
    this.requestSpecification.auth().preemptive().basic(username, password);
    return this;
  }


  /**
   * Bearer token HTTP auth
   *
   * @param token credential
   * @return Request
   */
  public Request bearerAuth(String token) {
    this.requestSpecification.header("Authorization", "Bearer " + token);
    return this;
  }

  public Request contentType(ContentType contentType) {
    this.requestSpecification.contentType(contentType);
    return this;
  }

  public Request contentType(String contentType) {
    this.requestSpecification.contentType(contentType);
    return this;
  }

  /**
   * Add soap custom ContentType
   *
   * @param action to include in ContentType
   * @return Request instance
   */
  public Request soapContentType(String action) {
    this.requestSpecification
      .contentType("application/soap+xml;charset=UTF-8;action=\"" + action + "\"");
    return this;
  }

  /**
   * Add soap action
   *
   * @param action to include in ContentType
   * @return Request instance
   */
  public Request soapAction(String action) {
    return this;
  }

  /**
   * Add soap TO service
   *
   * @param service to include in ContentType
   * @return Request instance
   */
  public Request soapToService(String service) {
    return this;
  }

  /**
   * Soap Header Authentication
   *
   * @param username to authenticate
   * @param password to authenticate
   * @return request instance
   */
  public Request soapAuthentication(String username, String password) {
    return this;
  }

  public Request body(String body) {
    this.requestSpecification.body(body);
    return this;
  }

  public Request body(Object body) {
    this.requestSpecification.body(body);
    return this;
  }

  public Request body(File body) {
    this.requestSpecification.body(body);
    return this;
  }

  public Request header(Header header) {
    this.requestSpecification.header(header);
    return this;
  }

  public Request header(String header, String value) {
    this.requestSpecification.header(header, value);
    return this;
  }

  public Request headers(Map<String, ?> headers) {
    this.requestSpecification.headers(headers);
    return this;
  }

  /**
   * Base path (http://service.com[/api/v1])
   *
   * @param basePath path to include between baseUri and service
   * @return instance
   */
  public Request basePath(String basePath) {
    this.requestSpecification.basePath(basePath);
    return this;
  }

  public Request formParam(String name, String value) {
    this.requestSpecification.formParam(name, value);
    return this;
  }

  public Request formParams(Map<String, ?> params) {
    this.requestSpecification.formParams(params);
    return this;
  }

  public Request param(String name, String value) {
    this.requestSpecification.param(name, value);
    return this;
  }

  public Request param(String param) {
    this.requestSpecification.param(param);
    return this;
  }

  public Request params(Map<String, ?> params) {
    this.requestSpecification.params(params);
    return this;
  }

  public Request pathParam(String name, String value) {
    this.requestSpecification.pathParam(name, value);
    return this;
  }

  public Request pathParam(Map<String, ?> pathParams) {
    this.requestSpecification.pathParams(pathParams);
    return this;
  }

  public Request queryParam(String name, Object... values) {
    this.requestSpecification.queryParam(name, values);
    return this;
  }

  public Request queryParam(String name, Collection<?> values) {
    this.requestSpecification.queryParam(name, values);
    return this;
  }

  public Request queryParams(Map<String, ?> params) {
    this.requestSpecification.queryParams(params);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param file to load
   * @return Request instance
   */
  public Request multiPart(File file) {
    this.requestSpecification.multiPart(file);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param multiPartSpecification to load
   * @return Request instance
   */
  public Request multiPart(MultiPartSpecification multiPartSpecification) {
    this.requestSpecification.multiPart(multiPartSpecification);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 String
   * @param var2 String
   * @param var3 String
   * @return Request instance
   */
  public Request multiPart(String var1, String var2, String var3) {
    this.requestSpecification.multiPart(var1, var2, var3);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 String
   * @param var2 String
   * @return Request instance
   */
  public Request multiPart(String var1, String var2) {
    this.requestSpecification.multiPart(var1, var2);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 String
   * @param var2 String
   * @param var3 InputStream
   * @param var4 String
   * @return Request instance
   */
  public Request multiPart(String var1, String var2, InputStream var3, String var4) {
    this.requestSpecification.multiPart(var1, var2, var3, var4);
    return this;
  }

  /**
   * @param var1 String
   * @param var2 String
   * @param var3 InputStream
   * @return Request instance
   */
  public Request multiPart(String var1, String var2, InputStream var3) {
    this.requestSpecification.multiPart(var1, var2, var3);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 String
   * @param var2 String
   * @param var3 byte
   * @param var4 String
   * @return Request instance
   */
  public Request multiPart(String var1, String var2, byte[] var3, String var4) {
    this.requestSpecification.multiPart(var1, var2, var3, var4);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 String
   * @param var2 String
   * @param var3 byte
   * @return Request instance
   */
  public Request multiPart(String var1, String var2, byte[] var3) {
    this.requestSpecification.multiPart(var1, var2, var3);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 String
   * @param var2 String
   * @param var3 Object
   * @param var4 String
   * @return Request instance
   */
  public Request multiPart(String var1, String var2, Object var3, String var4) {
    this.requestSpecification.multiPart(var1, var2, var3, var4);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 String
   * @param var2 Object
   * @param var3 String
   * @return Request instance
   */
  public Request multiPart(String var1, Object var2, String var3) {
    this.requestSpecification.multiPart(var1, var2, var3);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 var1
   * @param var2 var2
   * @return Request instance
   */
  public Request multiPart(String var1, Object var2) {
    this.requestSpecification.multiPart(var1, var2);
    return this;
  }

  /**
   * Multipart wrap method
   *
   * @param var1 var1
   * @param var2 var2
   * @param var3 var3
   * @return Request instance
   */
  public Request multiPart(String var1, File var2, String var3) {
    this.requestSpecification.multiPart(var1, var2, var3);
    return this;
  }


  /**
   * Multipart wrap method
   *
   * @param name to apply
   * @param file to load
   * @return Request instance
   */
  public Request multiPart(String name, File file) {
    this.requestSpecification.multiPart(name, file);
    return this;
  }

  public Request when() {
    return this;
  }

  public Request config(RestAssuredConfig config) {
    this.requestSpecification.config(config);
    return this;
  }

  public Request basic(String clientId, String clientSecret) {
    this.requestSpecification.auth().preemptive().basic(clientId, clientSecret);
    return this;
  }

  private Response sendRequest(Method method) {
    ValidatableResponse response = this.requestSpecification
      .request(method)
      .then();

    if (!Objects.isNull(response) && !Objects.isNull(response.extract())) {
      return new Response(response.extract().response());
    }

    return null;
  }

  private Response sendRequest(Method method, String path) {
    ValidatableResponse response = this.requestSpecification
      .basePath(path)
      .request(method)
      .then();

    if (!Objects.isNull(response) && !Objects.isNull(response.extract())) {
      return new Response(response.extract().response());
    }

    return null;
  }

  private Response waitableRequest(Method method) {
    Response response = sendRequest(method);
    if (!Objects.isNull(response)
      && !Objects.isNull(this.await)
      && !Objects.isNull(response.getResponse())
      && response.getResponse().getStatusCode() != this.expectedStatus) {
      this.await
        .until(() -> sendRequest(method).getResponse().getStatusCode() == this.expectedStatus);
    }
    return response;
  }

  private Response waitableRequest(Method method, String path) {
    Response response = sendRequest(method, path);
    if (!Objects.isNull(response)
      && !Objects.isNull(this.await)
      && !Objects.isNull(response.getResponse())
      && response.getResponse().getStatusCode() != this.expectedStatus) {
      this.await.until(() ->
        sendRequest(method, path).getResponse().getStatusCode() == this.expectedStatus);
    }
    return response;
  }


  public Response options() {
    return waitableRequest(Method.OPTIONS);
  }

  public Response options(String path) {
    return waitableRequest(Method.OPTIONS, path);
  }

  public Response post() {
    return waitableRequest(Method.POST);
  }

  public Response post(String path) {
    return waitableRequest(Method.POST, path);
  }

  public Response get() {
    return waitableRequest(Method.GET);
  }

  public Response get(String path) {
    return waitableRequest(Method.GET, path);
  }

  public Response put() {
    return waitableRequest(Method.PUT);
  }

  public Response put(String path) {
    return waitableRequest(Method.PUT, path);
  }

  public Response patch() {
    return waitableRequest(Method.PATCH);
  }

  public Response patch(String path) {
    return waitableRequest(Method.PATCH, path);
  }

  public Response delete() {
    return waitableRequest(Method.DELETE);
  }

  public Response delete(String path) {
    return waitableRequest(Method.DELETE, path);
  }
}