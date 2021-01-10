package com.example.demo.service;

import com.example.demo.controller.request.SignupRequest;
import com.example.demo.controller.response.SignupResponse;
import com.example.demo.dto.AccountDTO;
import com.example.demo.entity.Account;
import com.example.demo.exception.HttpUtil.HttpStatus;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 3:08 PM Michael Wijaya Exp $
 */
@Service
public class SignupService {

  @Autowired
  private AccountRepository repository;

  public SignupResponse create(SignupRequest signupRequest) {

    if (signupRequest.getUserId().length() < 6 || signupRequest.getUserId().length() > 20) {
      throw new ValidationException("Account creation failed",
          HttpStatus.BAD_REQUEST.getStatusCode(), "required user_id and password");
    }

    if (signupRequest.getPassword().length() < 8 || signupRequest.getPassword().length() > 20) {
      throw new ValidationException("Account creation failed",
          HttpStatus.BAD_REQUEST.getStatusCode(), "required user_id and password");
    }
    if (signupRequest.getUserId().isEmpty() || signupRequest.getPassword().isEmpty()) {
      throw new ValidationException("Account creation failed",
          HttpStatus.BAD_REQUEST.getStatusCode(), "required user_id and password");
    }

    Optional<Account> existingAccount = repository.findById(signupRequest.getUserId());
    if (existingAccount.isPresent()) {
      throw new ValidationException("Account creation failed",
          HttpStatus.BAD_REQUEST.getStatusCode(), "this user_id is already taken");
    }
    Account account = new Account()
        .setUserId(signupRequest.getUserId())
        .setPassword(signupRequest.getPassword())
        .setNickname(signupRequest.getUserId());

    repository.save(account);
    return new SignupResponse().setMessage("Account successfully created")
        .setUser(new AccountDTO().setUserId(account.getUserId())
            .setNickname(account.getNickname()));
  }
}
