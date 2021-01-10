package com.example.demo.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 3:08 PM Michael Wijaya Exp $
 */
@Data
@Accessors(chain = true)
public class SignupRequest {

  private String userId;
  private String password;
}
