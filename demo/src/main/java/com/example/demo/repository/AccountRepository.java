package com.example.demo.repository;

import com.example.demo.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Michael Wijaya
 * @version , v0.1 Jan 10, 2021 3:12 PM Michael Wijaya Exp $
 */
public interface AccountRepository extends
    JpaRepository<Account, String> {

  Optional<Account> findByUserIdAndPassword(String userId, String password);
}
