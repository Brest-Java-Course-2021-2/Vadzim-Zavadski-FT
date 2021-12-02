package com.zavadski.service;

import com.zavadski.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAllTeams();

    Team getTeamById(Integer teamId);

    Integer create(Team team);

    Integer update(Team team);

    Integer delete(Integer teamId);

    Integer count();
}