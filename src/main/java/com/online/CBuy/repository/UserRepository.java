package com.online.CBuy.repository;

import com.online.CBuy.document.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<User, String> {
    // Custom query methods (nếu cần)
    int deleteUsersById(@Param("id") ObjectId id);
}
