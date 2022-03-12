package com.zavadski.rest;

import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@Api(tags = {"Player controllers"})
public class PlayerController {

    private static final Logger logger = LogManager.getLogger(PlayerController.class);
    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @ApiOperation(value = "Returns player list")
    @GetMapping(value = "/players")
    public final Collection<Player> getAllPlayers() {

        logger.debug("rest-app: getAllPlayers()");
        return playerService.getAllPlayers();
    }

    @ApiOperation(value = "Returns one particular player")
    @GetMapping(value = "/players/{id}")
    public final Player getPlayerById(@PathVariable Integer id) {

        logger.debug("rest-app: getPlayerById()");
        return playerService.getPlayerById(id);
    }

    @ApiOperation(value = "Creates player instance")
    @PostMapping(path = "/players", consumes = "application/json", produces = "application/json")
    public final ResponseEntity<Integer> createPlayer(@RequestBody Player player) {

        logger.debug("rest-app: createPlayer({})", player);
        Integer id = playerService.create(player);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates particular player instance")
    @PutMapping(value = "/players", consumes = {"application/json"}, produces = {"application/json"})
    public final ResponseEntity<Integer> updatePlayer(@RequestBody Player player) {

        logger.debug("rest-app: updatePlayer({})", player);
        int result = playerService.update(player);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes particular player instance")
    @DeleteMapping(value = "/players/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deletePlayer(@PathVariable Integer id) {
        int result = playerService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
