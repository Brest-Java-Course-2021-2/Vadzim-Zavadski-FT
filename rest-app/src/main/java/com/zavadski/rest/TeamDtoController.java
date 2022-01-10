package com.zavadski.rest;

import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.TeamDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Api(tags = {"Team dto controller"})
public class TeamDtoController {

    private static final Logger logger = LogManager.getLogger(TeamDtoController.class);

    private final TeamDtoService teamDtoService;

    public TeamDtoController(TeamDtoService teamDtoService) {
        this.teamDtoService = teamDtoService;
    }

    @ApiOperation(value = "Returns team list with average age and number of players in team")
    @GetMapping(value = "team_dtos")
    public final Collection<TeamDto> teamDtos() {
        logger.debug("rest-app: teamDtos()");
        return teamDtoService.findAllWithNumberOfPlayers();
    }
}
