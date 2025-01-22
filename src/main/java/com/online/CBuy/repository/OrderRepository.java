package com.online.CBuy.repository;

import com.online.CBuy.document.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query(value = "{'userId': ?0}")
    List<Order> findOneByUserId(@Param("userId") ObjectId id);

    @Query(value = "{'_id': ?0}")
    Order findOneById(@Param("id") ObjectId id);
}
