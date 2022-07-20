package com.zavadski.mongo.repository;

import com.zavadski.mongo.model.document.PlayersDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteToMongoRepository
        extends MongoRepository<PlayersDocument, String> {
}
