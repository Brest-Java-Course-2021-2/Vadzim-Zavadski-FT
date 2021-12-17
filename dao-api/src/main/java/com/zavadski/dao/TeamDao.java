package com.zavadski.dao;

import com.zavadski.model.Team;

import java.util.List;

public interface TeamDao {

    List<Team> findAll();

    Team getTeamById(Integer teamId);

    Integer create(Team team);

    Integer update(Team team);

    Integer delete(Integer teamId);

    Integer count();

    boolean isTeamWithPlayers(Integer teamId);

}
