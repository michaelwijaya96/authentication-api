/**
 * DANA Indonesia. Copyright (c) 2016‐2020 All Rights Reserved.
 */
package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Uriando Tjiangdiono <uriando.tjiangdiono@dana.id>
 * @version $Id: ValidationError.java, v 0.1 2020‐07‐03 18.56 uriando.tjiangdiono Exp $$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {
  private String field;
  private String message;
}
