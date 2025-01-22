package com.online.CBuy.repository;

import com.online.CBuy.document.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{'sellerId': ?0}")
    List<Product> findOneBySellerId(@Param("sellerId") ObjectId id);

    @Query(value = "{'_id': ?0}")
    Product findOneById(@Param("id") ObjectId id);

    Optional<Product> findByName(String name);
}
