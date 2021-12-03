package com.zavadski.service;

import com.zavadski.model.dto.TeamDto;

import java.util.List;

public interface TeamDtoService {

    /**
     * Get list of team Dto.
     *
     * @return list of team Dto.
     */
    List<TeamDto> findAllWithNumberOfPlayers();

}
