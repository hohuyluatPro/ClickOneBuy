package com.online.CBuy.repository;

import com.online.CBuy.document.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    @Query(value = "{'userId': ?0}")
    Cart findOneByUserId(@Param("userId") ObjectId id);

    @Query(value = "{'_id': ?0}")
    Cart findOneById(@Param("id") ObjectId id);
}
