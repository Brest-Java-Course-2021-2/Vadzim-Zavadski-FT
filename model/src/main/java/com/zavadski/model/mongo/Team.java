package com.zavadski.model.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Team {

    private String teamName;
    private List<Player> players;

    public static Team fromTeam(com.zavadski.model.Team team) {
        Team teamMongo = new Team();
        teamMongo.setTeamName(team.getTeamName());
        return teamMongo;
    }
}
