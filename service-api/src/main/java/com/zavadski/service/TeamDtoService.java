package com.zavadski.service;

import com.zavadski.model.dto.TeamDto;

import java.util.List;

public interface TeamDtoService {

    List<TeamDto> findAllWithNumberOfPlayers();

}
