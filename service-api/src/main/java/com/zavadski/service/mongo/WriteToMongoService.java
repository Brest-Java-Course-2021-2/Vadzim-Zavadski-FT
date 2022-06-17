package com.zavadski.service.mongo;

import com.zavadski.model.mongo.document.PlayersDocument;

public interface WriteToMongoService {

    PlayersDocument createCollection();

}
