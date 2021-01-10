/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * CMS Exception Handler
 *
 * @author DTC-Stefan
 * @version $Id: CmsExceptionHandler.java, v 0.1 Jul 11, 2018 1:07:22 PM DTC-Stefan Exp $
 */
@ControllerAdvice
public class CmsExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ValidationException.class})
  protected ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex,
      WebRequest request) {
    HttpStatus httpStatus = HttpStatus.resolve(ex.getHttpStatusCode());
    if (httpStatus == null) {
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    ErrorResponse errorResponse = createErrorResponseMessage(httpStatus.value(), ex.getMessage(),
        ex.getOncom());
    return new ResponseEntity<>(errorResponse, null, httpStatus);
  }

  private ErrorResponse createErrorResponseMessage(int status, String message, String cause) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCause(cause);
    errorResponse.setMessage(message);
    return errorResponse;
  }

}
