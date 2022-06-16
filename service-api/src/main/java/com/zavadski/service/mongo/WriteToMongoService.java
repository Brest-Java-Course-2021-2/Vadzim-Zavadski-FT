package com.zavadski.service.mongo;

import com.zavadski.model.mongo.PlayersByAgeAndTeam;

public interface WriteToMongoService {

    PlayersByAgeAndTeam getPlayersByTimeInterval(String timeIntervalName, int min, int max);

    void createCollection();

}
