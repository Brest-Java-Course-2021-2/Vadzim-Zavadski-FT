package com.zavadski.dao;

import com.zavadski.model.Team;

import java.util.List;

public interface TeamDao {

    /**
     * Get all teams from the database.
     *
     * @return teams list.
     */
    List<Team> findAll();

    /**
     * Get team by Id.
     *
     * @param teamId team Id.
     * @return team.
     */
    Team getTeamById(Integer teamId);

    Integer create(Team team);

    Integer update(Team team);

    Integer delete(Integer teamId);

    Integer count();

}
