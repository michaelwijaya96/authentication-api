package com.example.demo.controller;

import com.example.demo.controller.response.GetUserResponse;
import com.example.demo.controller.response.PatchUserRequest;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 3:06 PM Michael Wijaya Exp $
 */
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<GetUserResponse> getUser(
      @RequestHeader("Authorization") String authorization,
      @PathVariable(name = "id") String id) {
    return ResponseEntity.ok(userService.getUser(authorization, id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetUserResponse> patchUser(

      @RequestHeader("Authorization") String authorization,
      @PathVariable(name = "id") String id,
      @RequestBody PatchUserRequest request

  ){

    return ResponseEntity.ok(userService.patchUser(authorization, id, request));
  }
}
