/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true
)
public class ErrorResponse {

  private String message;
  @JsonInclude(Include.NON_NULL)
  private String cause;

  /**
   * Default constructor
   */
  public ErrorResponse() {
  }

  public ErrorResponse(String cause, String message) {
    this();
    this.cause = cause;
    this.message = message;
  }


}
