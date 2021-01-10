package com.example.demo.controller;

import com.example.demo.controller.request.SignupRequest;
import com.example.demo.controller.response.SignupResponse;
import com.example.demo.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 12:33 PM Michael Wijaya Exp $
 */
@RestController
@RequestMapping("/signup")
public class SignupController {

  @Autowired
  private SignupService service;

  @PostMapping
  private ResponseEntity<SignupResponse> create(@RequestBody SignupRequest request){
    return ResponseEntity.ok(service.create(request));
  }
}
