package com.zavadski.mongo.service;

import com.zavadski.mongo.model.document.PlayersDocument;

public interface WriteToMongoService {

    PlayersDocument createCollection();

}
