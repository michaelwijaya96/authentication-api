package com.example.demo.controller.response;

import com.example.demo.dto.AccountDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 3:08 PM Michael Wijaya Exp $
 */
@Data
@Accessors(chain = true)
public class SignupResponse {

  private String message;
  private AccountDTO user;
}
