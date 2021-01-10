package com.example.demo.controller.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Michael Wijaya
 * @version \, v0.1 Jan 10, 2021 4:26 PM Michael Wijaya Exp $
 */
@Data
@Accessors(chain = true)
public class PatchUserRequest {
  private String nickname;
  private String comment;
}
