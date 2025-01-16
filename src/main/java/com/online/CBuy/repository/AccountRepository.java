package com.online.CBuy.repository;

import com.online.CBuy.document.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByUsername(String username); // Tìm kiếm tài khoản theo username
}

