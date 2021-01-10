package com.example.demo.controller;

import com.example.demo.controller.response.CloseResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 4:30 PM Michael Wijaya Exp $
 */
@RestController
public class CloseController {

  @Autowired
  private UserService userService;

  @PostMapping("/close")
  public ResponseEntity<CloseResponse> close(@RequestHeader("Authorization") String authorization){

    return ResponseEntity.ok(userService.close(authorization));
  }
}
