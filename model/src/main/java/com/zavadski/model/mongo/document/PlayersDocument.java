package com.zavadski.model.mongo.document;

import com.zavadski.model.mongo.PlayersByAgeAndTeam;
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
    private String dateOfCreating;
    private List<PlayersByAgeAndTeam> playersByAgeAndTeams;

    public PlayersDocument(String dateOfCreating, List<PlayersByAgeAndTeam> playersByAgeAndTeams) {
        this.dateOfCreating = dateOfCreating;
        this.playersByAgeAndTeams = playersByAgeAndTeams;
    }
}
