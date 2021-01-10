package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 3:06 PM Michael Wijaya Exp $
 */
@Entity
@Data
@Accessors(chain = true)
public class Account {

  @Id
  @Column(unique = true)
  private String userId;
  private String password;
  private String nickname;
  private String comment;
}
