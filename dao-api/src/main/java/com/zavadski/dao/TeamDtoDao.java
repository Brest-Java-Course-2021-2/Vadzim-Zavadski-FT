package com.zavadski.dao;

import com.zavadski.model.dto.TeamDto;

import java.util.List;

/**
 * TeamDto DAO Interface.
 */
public interface TeamDtoDao {

    /**
     * Get all teams with number of players in team.
     *
     * @return teams list.
     */
    List<TeamDto> findAllWithNumberOfPlayers();

}
