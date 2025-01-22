package com.online.CBuy.repository;

import com.online.CBuy.document.Rate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends MongoRepository<Rate, String> {
    @Query(value = "{'userId': ?0}")
    List<Rate> findOneByUserId(@Param("userId") ObjectId id);

    @Query(value = "{'productId': ?0}")
    List<Rate> findByProductId(@Param("productId") ObjectId id);

    @Query(value = "{ $and: [{'productId': ?0}, {'userId': ?1}]}")
    Rate findByProductIdAndUserId(@Param("productId") ObjectId productId, @Param("userId") ObjectId userId);

    @Query(value = "{'_id': ?0}")
    Rate findOneById(@Param("id") ObjectId id);
}
