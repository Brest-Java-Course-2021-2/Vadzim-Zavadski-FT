package com.zavadski.service;

import com.zavadski.model.Team;

public interface TeamService {

    Team getTeamById(Integer teamId);

    Integer create(Team team);

    Integer update(Team team);

    Integer delete(Integer teamId);

    Integer count();
}