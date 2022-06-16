package com.zavadski.dao.api.mongo;

import com.zavadski.model.mongo.document.PlayersDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteToMongoRepository
        extends MongoRepository<PlayersDocument, String> {
}
