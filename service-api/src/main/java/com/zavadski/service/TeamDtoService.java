package com.zavadski.service;

import com.zavadski.model.dto.TeamDto;

import java.util.Collection;

public interface TeamDtoService {

    Collection<TeamDto> findAllWithNumberOfPlayers();

}
