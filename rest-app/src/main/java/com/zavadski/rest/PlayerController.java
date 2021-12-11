package com.zavadski.rest;

import com.zavadski.dao.PlayerDaoJDBCImpl;
import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
public class PlayerController {

    private static final Logger logger = LogManager.getLogger(PlayerDaoJDBCImpl.class);

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/players")
    public final Collection<Player> players() {

        logger.debug("players()");
        return playerService.findAllPlayers();
    }

    @GetMapping(value = "/players/{id}")
    public final Optional<Player> getPlayerById(@PathVariable Integer id) {

        logger.debug("player()");
        return playerService.getPlayerById(id);
    }

    @PostMapping(value = "/players", consumes = "application/json", produces = "application/json")
    public final ResponseEntity<Integer> createPlayer(@RequestBody Player player) {

        logger.debug("createPlayer({})", player);
        Integer id = playerService.create(player);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "/players", consumes = {"application/json"}, produces = {"application/json"})
    public final ResponseEntity<Integer> updatePlayer(@RequestBody Player player) {

        logger.debug("updatePlayer({})", player);
        int result = playerService.update(player);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/players/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deletePlayer(@PathVariable Integer id) {

        int result = playerService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
