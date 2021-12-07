package com.zavadski.rest;

import com.zavadski.dao.TeamDaoJDBCImpl;
import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.TeamDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TeamDtoController {

    private static final Logger logger = LogManager.getLogger(TeamDaoJDBCImpl.class);

    private final TeamDtoService teamDtoService;

    public TeamDtoController(TeamDtoService teamDtoService) {
        this.teamDtoService = teamDtoService;
    }

    @GetMapping(value = "teams_dto")
    public final Collection<TeamDto> getTeamById(@PathVariable Integer id) {

        logger.debug("team()");
        return teamDtoService.findAllWithNumberOfPlayers();
    }
}
