package com.zavadski.rest;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@Api(tags = {"Team controllers"})
public class TeamController {

    private static final Logger logger = LogManager.getLogger(TeamController.class);

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @ApiOperation(value = "Returns team list")
    @GetMapping(value = "/teams")
    public final Collection<Team> getAllTeams() {

        logger.debug("rest-app: findAllTeams()");
        return teamService.getAllTeams();
    }

    @ApiOperation(value = "Returns one particular team")
    @GetMapping(value = "/teams/{id}")
    public final Team getTeamById(@PathVariable Integer id) {

        logger.debug("rest-app: getTeamById()");
        return teamService.findTeamById(id);
    }

    @ApiOperation(value = "Creates team instance")
    @PostMapping(path = "/teams", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createTeam(@RequestBody Team team) {

        logger.debug("rest-app: createTeam({})", team);
        Integer id = teamService.create(team);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates particular team instance")
    @PutMapping(value = "/teams", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateTeam(@RequestBody Team team) {

        logger.debug("rest-app: updateTeam({})", team);
        int result = teamService.updateTeam(team);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes particular teams instance")
    @DeleteMapping(value = "/teams/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteTeam(@PathVariable Integer id) {

        int result = teamService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns teams count")
    @GetMapping(value = "/teams/count")
    public final Integer count() {

        logger.debug("rest-app: count()");
        return teamService.count();
    }

    @GetMapping("/teams/check/{teamId}")
    public boolean isTeamWithPlayers(@PathVariable int teamId) {
        return teamService.checkOnTeamWithPlayers(teamId);
    }

    @GetMapping("/teams/unique/{teamName}")
    public boolean checkTeamOnUnique(@PathVariable String teamName) {
        return teamService.checkTeamOnUnique(teamName);
    }

}
