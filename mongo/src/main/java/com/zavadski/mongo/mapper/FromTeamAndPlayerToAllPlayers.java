package com.zavadski.mongo.mapper;

import com.zavadski.mongo.mapper.AllPlayers.TeamMongo;
import com.zavadski.mongo.mapper.AllPlayers.TeamMongo.PlayerMongo;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FromTeamAndPlayerToAllPlayers {

    private final TeamService teamService;
    private final PlayerService playerService;

    public FromTeamAndPlayerToAllPlayers(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
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
                        .stream().filter(teamMongo1 -> !teamMongo1.getPlayers().isEmpty()).collect(Collectors.toList())
                ));
    }

}
