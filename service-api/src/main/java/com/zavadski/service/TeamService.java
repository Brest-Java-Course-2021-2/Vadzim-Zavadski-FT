package com.zavadski.service;

import com.zavadski.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    Team findTeamById(Integer teamId);

    Integer create(Team team);

    Integer updateTeam(Team team);

    Integer delete(Integer teamId);

    Integer count();

    boolean checkOnTeamWithPlayers(Integer teamId);

    boolean checkTeamOnUnique(String teamName);

}
