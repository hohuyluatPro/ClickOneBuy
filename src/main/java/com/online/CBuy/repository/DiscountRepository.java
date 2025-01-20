package com.online.CBuy.repository;

import com.online.CBuy.document.Discount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiscountRepository extends MongoRepository<Discount, String> {
    @Query(value = "{'_id': ?0}")
    Discount findOneById(@Param("id") ObjectId id);

    Optional<Discount> findByName(String name);
}
