package com.example.demo.service;

import com.example.demo.controller.response.CloseResponse;
import com.example.demo.controller.response.GetUserResponse;
import com.example.demo.controller.response.PatchUserRequest;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Account;
import com.example.demo.exception.HttpUtil.HttpStatus;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AccountRepository;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michael Wijaya
 * @version $Id: UserService.java, v0.1 Jan 10, 2021 3:50 PM Michael Wijaya Exp $
 */
@Service
public class UserService {

  @Autowired
  private AccountRepository accountRepository;

  public GetUserResponse getUser(String authorization, String id) {
    Account account = accountRepository.findById(id)
        .orElseThrow(
            () -> new ValidationException("No User Found", HttpStatus.NOT_FOUND.getStatusCode(),
                null));
    validateAuthorization(authorization, account);
    return new GetUserResponse().setMessage("User details for user_id")
        .setUser(new UserDTO().setUserId(id)
            .setComment(account.getComment())
            .setNickname(account.getNickname())
        );
  }

  public GetUserResponse patchUser(String authorization, String id, PatchUserRequest request) {
    Account account = accountRepository.findById(id)
        .orElseThrow(
            () -> new ValidationException("No User Found", HttpStatus.NOT_FOUND.getStatusCode(),
                null));

    validateAuthorization(authorization, account);
    account = accountRepository.save(account.setNickname(request.getNickname())
        .setComment(request.getComment()));
    return new GetUserResponse().setUser(new UserDTO().setNickname(account.getNickname())
        .setComment(account.getComment())
        .setUserId(id));
  }


  public CloseResponse close(String authorization) {

    if (!authorization.startsWith("Basic ")) {
      throw new ValidationException("Authentication Failed", HttpStatus.UNAUTHORIZE.getStatusCode(),
          null);
    }

    String baseEncodedUserIdAndPassword = authorization.substring("Basic ".length());
    byte[] bytes = Base64.getDecoder()
        .decode(baseEncodedUserIdAndPassword.getBytes());

    String userIdAndPassword = new String(bytes);

    String userId = userIdAndPassword.split(":")[0];
    String password = userIdAndPassword.split(":")[1];

    Account account = accountRepository.findById(userId)
        .orElseThrow(
            () -> new ValidationException("No User Found", HttpStatus.NOT_FOUND.getStatusCode(),
                null));

    if (account.getPassword().equalsIgnoreCase(password)) {
      throw new ValidationException("Authentication Failed", HttpStatus.UNAUTHORIZE.getStatusCode(),
          null);
    }

    return new CloseResponse().setMessage("Account and user successfully removed");
  }

  private void validateAuthorization(String authorization, Account account) {
    if (!authorization.startsWith("Basic ")) {
      throw new ValidationException("Authentication Failed", HttpStatus.UNAUTHORIZE.getStatusCode(),
          null);
    }

    String baseEncodedUserIdAndPassword = authorization.substring("Basic ".length());
    byte[] bytes = Base64.getDecoder()
        .decode(baseEncodedUserIdAndPassword.getBytes());

    String userIdAndPassword = new String(bytes);
    String password = userIdAndPassword.split(":")[1];
    if (account.getPassword().equalsIgnoreCase(password)) {
      throw new ValidationException("Authentication Failed", HttpStatus.UNAUTHORIZE.getStatusCode(),
          null);
    }
  }
}
