package com.online.CBuy.repository;

import com.online.CBuy.document.Account;
import com.online.CBuy.dto.GetAccountDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByUsername(String username); // Tìm kiếm tài khoản theo username

    @Query(value = "{'_id': ?0}")
    Account findOneById(@Param("id") ObjectId id);

    @Query(value = "{'_id': ?0}")
    GetAccountDto findAccountOneById(@Param("id") ObjectId id);

}

