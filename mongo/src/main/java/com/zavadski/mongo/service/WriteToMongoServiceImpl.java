package com.zavadski.mongo.service;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zavadski.mongo.mapper.FromTeamAndPlayerToAllPlayers;
import com.zavadski.mongo.model.PlayersDocument;
import com.zavadski.mongo.repository.WriteToMongoRepository;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WriteToMongoServiceImpl implements WriteToMongoService {

    private final FromTeamAndPlayerToAllPlayers fromTeamAndPlayerToAllPlayers;
    private final WriteToMongoRepository repository;

    public WriteToMongoServiceImpl(FromTeamAndPlayerToAllPlayers fromTeamAndPlayerToAllPlayers, WriteToMongoRepository repository) {
        this.fromTeamAndPlayerToAllPlayers = fromTeamAndPlayerToAllPlayers;
        this.repository = repository;
    }

    @Override
    public PlayersDocument createCollection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoDatabase db = mongoClient.getDatabase("Football-Teams").withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Document> collection = db.getCollection("football-teams");

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, uuuu"));

        PlayersDocument playersDocument = new PlayersDocument(
                currentDate,
                (List.of(fromTeamAndPlayerToAllPlayers.getPlayersByTimeInterval("under 18", 0, 18),
                        fromTeamAndPlayerToAllPlayers.getPlayersByTimeInterval("from 18 to 23", 18, 23),
                        fromTeamAndPlayerToAllPlayers.getPlayersByTimeInterval("from 23 to 28", 23, 28),
                        fromTeamAndPlayerToAllPlayers.getPlayersByTimeInterval("over 28", 28, 150)
                )));
        repository.deleteAll();
        repository.insert(playersDocument);
        return playersDocument;
    }
}
