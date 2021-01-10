package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 4:00 PM Michael Wijaya Exp $
 */
@Data
@Accessors(chain = true)
public class UserDTO {
  private String userId;
  private String nickname;
  private String comment;
}