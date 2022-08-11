package com.zavadski.mongo.service;

import com.zavadski.mongo.model.PlayersDocument;

public interface WriteToMongoService {

    PlayersDocument createCollection();

}
