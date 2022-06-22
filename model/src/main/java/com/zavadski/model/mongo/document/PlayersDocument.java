package com.zavadski.model.mongo.document;

import com.zavadski.model.mongo.AllPlayers;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "football-teams")
public class PlayersDocument {

    @Id
    private String id;
    private String date;
    private List<AllPlayers> players;

    public PlayersDocument(String date, List<AllPlayers> players) {
        this.date = date;
        this.players = players;
    }
}
