package com.zavadski.rest;

import com.zavadski.dao.TeamDaoJDBCImpl;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    private static final Logger logger = LogManager.getLogger(TeamDaoJDBCImpl.class);

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/teams/{id}")
    public final Team getTeamById(@PathVariable Integer id) {

        logger.debug("team()");
        return teamService.getTeamById(id);
    }

    @PostMapping(path = "/teams", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createTeam(@RequestBody Team team) {

        logger.debug("createTeam({})", team);
        Integer id = teamService.create(team);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/teams", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateTeam(@RequestBody Team team) {

        logger.debug("updateTeam({})", team);
        int result = teamService.update(team);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/teams/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteTeam(@PathVariable Integer id) {

        int result = teamService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
