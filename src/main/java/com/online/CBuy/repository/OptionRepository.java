package com.online.CBuy.repository;

import com.online.CBuy.document.Option;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OptionRepository extends MongoRepository<Option, String> {
    @Query(value = "{'_id': ?0}")
    Option findOneById(@Param("id") ObjectId id);

    Optional<Option> findByName(String name);
}
