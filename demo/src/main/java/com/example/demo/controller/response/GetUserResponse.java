package com.example.demo.controller.response;

import com.example.demo.dto.UserDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 4:00 PM Michael Wijaya Exp $
 */
@Data
@Accessors(chain = true)
public class GetUserResponse {
  private String message;
  private UserDTO user;
}


