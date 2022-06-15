package com.zavadski.mongo.service;

import com.zavadski.mongo.model.PlayerMongo;
import com.zavadski.mongo.model.PlayersByAgeAndTeam;
import com.zavadski.mongo.model.TeamMongo;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PlayersByInterval {

    private final TeamService teamService;
    private final PlayerService playerService;

    public PlayersByInterval(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    public PlayersByAgeAndTeam getPlayersByTimeInterval(String timeIntervalName, int min, int max) {

        return new PlayersByAgeAndTeam(timeIntervalName,
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
}
