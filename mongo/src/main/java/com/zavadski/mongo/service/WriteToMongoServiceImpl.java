package com.zavadski.mongo.service;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zavadski.mongo.model.AllPlayers;
import com.zavadski.mongo.model.PlayerMongo;
import com.zavadski.mongo.model.TeamMongo;
import com.zavadski.mongo.model.document.PlayersDocument;
import com.zavadski.mongo.repository.WriteToMongoRepository;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamService;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WriteToMongoServiceImpl implements WriteToMongoService {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final WriteToMongoRepository repository;

//    public WriteToMongoServiceImpl(TeamService teamService, @Qualifier("playerServiceImpl") PlayerService playerService, WriteToMongoRepository repository) {
public WriteToMongoServiceImpl(TeamService teamService, PlayerService playerService, WriteToMongoRepository repository) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.repository = repository;
    }

    public AllPlayers getPlayersByTimeInterval(String timeIntervalName, int min, int max) {

        return new AllPlayers(timeIntervalName,
                (teamService.getAllTeams().stream()
                        .map(TeamMongo::fromTeam)
                        .collect(Collectors.toList())
                        .stream().peek(teamMongo -> teamMongo.setPlayers(playerService.getAllPlayers().stream()
                                .filter(playerMongo -> Objects.equals(teamService.findTeamById(playerMongo.getTeamId()).getTeamName(), teamMongo.getTeamName()))
                                .map(PlayerMongo::fromPlayer)
                                .filter(playerMongo -> playerMongo.getAge() < max && playerMongo.getAge() >= min)
                                .collect(Collectors.toList())))
                        .collect(Collectors.toList())
                        .stream().filter(teamMongo -> !teamMongo.getPlayers().isEmpty()).collect(Collectors.toList())
                ));
    }

    @Override
    public PlayersDocument createCollection() {
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
                (List.of(getPlayersByTimeInterval("under 18", 0, 18),
                        getPlayersByTimeInterval("from 18 to 23", 18, 23),
                        getPlayersByTimeInterval("from 23 to 28", 23, 28),
                        getPlayersByTimeInterval("over 28", 28, 150)
                )));
        repository.insert(playersDocument);
        return playersDocument;
    }
}
