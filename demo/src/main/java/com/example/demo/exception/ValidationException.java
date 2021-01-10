/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private int httpStatusCode;
  private String oncom;

  /**
   * @param message
   * @param httpStatusCode
   */
  public ValidationException(String message, int httpStatusCode, String oncom) {
    super(message);
    this.httpStatusCode = httpStatusCode;
    this.oncom = oncom;
  }

}
