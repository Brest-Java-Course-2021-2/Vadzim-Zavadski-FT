package com.zavadski.rest.mongo;

import com.zavadski.model.mongo.document.PlayersDocument;
import com.zavadski.service.mongo.WriteToMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {

    private final WriteToMongoService writeToMongoService;

    @Autowired
    public MongoController(WriteToMongoService writeToMongoService) {
        this.writeToMongoService = writeToMongoService;
    }

    @GetMapping(value = "/write_to_mongo")
    public PlayersDocument writeToMongo() {

        return writeToMongoService.createCollection();
    }
}
