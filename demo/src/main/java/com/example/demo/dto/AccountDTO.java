package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 3:39 PM Michael Wijaya Exp $
 */
@Data
@Accessors(chain = true)
public class AccountDTO {
  private String userId;
  private String nickname;
}
