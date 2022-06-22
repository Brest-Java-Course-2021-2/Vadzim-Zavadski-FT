package com.zavadski.web_app.mongo;

import com.zavadski.service.mongo.WriteToMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MongoController {

    private final WriteToMongoService writeToMongoService;

    @Autowired
    public MongoController(WriteToMongoService writeToMongoService) {
        this.writeToMongoService = writeToMongoService;
    }

    @GetMapping(value = "/write_to_mongo")
    public String writeToMongo(Model model) {
        model.addAttribute("playersListMongo", writeToMongoService.createCollection());
        return "mongo";
    }

}
