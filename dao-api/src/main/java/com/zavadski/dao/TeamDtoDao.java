package com.zavadski.dao;

import com.zavadski.model.dto.TeamDto;

import java.util.List;

public interface TeamDtoDao {

    List<TeamDto> findAllWithNumberOfPlayers();

}
