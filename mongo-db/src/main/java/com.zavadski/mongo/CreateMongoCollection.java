package com.zavadski.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zavadski.mongo.service.PlayersByInterval;
import com.zavadski.mongo.document.PlayersDocument;
import com.zavadski.mongo.repository.PlayersByAgeRepository;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CreateMongoCollection {

    private final PlayersByInterval playersByInterval;
    private final PlayersByAgeRepository repository;

    @Autowired
    public CreateMongoCollection(PlayersByInterval playersByInterval, PlayersByAgeRepository repository) {
        this.playersByInterval = playersByInterval;
        this.repository = repository;
    }

    public void createCollection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoDatabase db = mongoClient.getDatabase("Football-Teams").withCodecRegistry(pojoCodecRegistry);
        db.getCollection("football-teams").drop();
        MongoCollection<Document> coll = db.getCollection("football-teams");

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, uuuu"));

        PlayersDocument playersDocument = new PlayersDocument(
                currentDate,
                (List.of(playersByInterval.getPlayersByTimeInterval("under 18",0,18),
                        playersByInterval.getPlayersByTimeInterval("from 18 to 23", 18,23),
                        playersByInterval.getPlayersByTimeInterval("from 23 to 28", 23,28),
                        playersByInterval.getPlayersByTimeInterval("over 28",28, 150)
                )));

        repository.insert(playersDocument);

    }
}
