package com.zavadski.web_app.mongo;

import com.zavadski.mongo.service.PlayersByInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MongoController {

    private final PlayersByInterval playersByInterval;

    @Autowired
    public MongoController(PlayersByInterval playersByInterval) {
        this.playersByInterval = playersByInterval;
    }


    @GetMapping(value = "/write_to_mongo")
    public final String writeToMongo() {

        return "/write_to_mongo";
    }

}
