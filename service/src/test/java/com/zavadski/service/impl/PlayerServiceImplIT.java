package com.zavadski.service.impl;

import com.zavadski.model.Player;
import com.zavadski.model.Team;
import com.zavadski.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class PlayerServiceImplIT {

    private final Logger logger = LogManager.getLogger(PlayerServiceImplIT.class);

    @Autowired
    PlayerService playerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllPlayers() {
        logger.debug("Execute test: findAllPlayers()");
        assertNotNull(playerService);
        assertNotNull(playerService.findAllPlayers());
    }

    @Test
    void getPlayerById() {
        logger.debug("Execute test: getPlayerById()");

        List<Player> players = playerService.findAllPlayers();
        if (players.size() == 0) {
            playerService.create(new Player("Topol"));
            players = playerService.findAllPlayers();
        }

        Player playerSrc = players.get(0);
        Player playerDst = playerService.getPlayerById(playerSrc.getPlayerId());
        assertEquals(playerSrc.getFirstName(), playerDst.getFirstName());
    }

    @Test
    void create() {
        logger.debug("Execute test: createPlayer()");
        assertNotNull(playerService);
        int teamSizeBefore = playerService.findAllPlayers().size();
        Player player = new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1);
        Integer newPlayerId = playerService.create(player);
        assertNotNull(newPlayerId);
        assertEquals((int) teamSizeBefore, playerService.findAllPlayers().size() - 1);
    }

    @Test
    void tryToCreateEqualsPlayers() {
        logger.debug("Execute test: tryToCreateEqualsPlayers()");
        assertNotNull(playerService);
        Player player = new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1);

        assertThrows(IllegalArgumentException.class, () -> {
            playerService.create(player);
            playerService.create(player);
        });
    }

    @Test
    void updatePlayer() {
        logger.debug("Execute test: updatePlayer()");
        List<Player> players = playerService.findAllPlayers();
        if (players.size() == 0) {
            playerService.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
            players = playerService.findAllPlayers();
        }

        Player teamSrc = players.get(0);
        Player teamDst = playerService.getPlayerById(teamSrc.getPlayerId());
        assertEquals(teamSrc.getFirstName(), teamDst.getFirstName());
    }

    @Test
    void deletePlayer() {
        logger.debug("Execute test: deletePlayer()");
        playerService.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
        List<Player> players = playerService.findAllPlayers();

        playerService.delete(players.get(players.size() - 1).getPlayerId());
        assertEquals(players.size() - 1, playerService.findAllPlayers().size());
    }
}