package com.zavadski.model.mongo;

import com.zavadski.model.Team;
import lombok.Data;

import java.util.List;

@Data
public class TeamMongo {

    private String teamName;
    private List<PlayerMongo> players;

    public static TeamMongo fromTeam(Team team) {
        TeamMongo teamMongo = new TeamMongo();
        teamMongo.setTeamName(team.getTeamName());
        return teamMongo;
    }
}
