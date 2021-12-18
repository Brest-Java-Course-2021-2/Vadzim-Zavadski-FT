package com.zavadski.dao;

import com.zavadski.model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
class PlayerDaoJdbcIT {

    private final Logger logger = LogManager.getLogger(PlayerDaoJdbcIT.class);

    private PlayerDaoJDBCImpl playerDaoJDBC;

    public PlayerDaoJdbcIT(@Autowired PlayerDao playerDaoJdbc) {
        this.playerDaoJDBC = (PlayerDaoJDBCImpl) playerDaoJdbc;
    }

    @Test
    void findAllPlayers() {
        logger.debug("Execute test: findAllPlayers()");
        assertNotNull(playerDaoJDBC);
        assertNotNull(playerDaoJDBC.findAllPlayers());
    }

    @Test
    void create() {
        logger.debug("Execute test: createPlayer()");
        assertNotNull(playerDaoJDBC);
        int teamSizeBefore = playerDaoJDBC.findAllPlayers().size();
        Player player = new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1);
        Integer newPlayerId = playerDaoJDBC.create(player);
        assertNotNull(newPlayerId);
        assertEquals((int) teamSizeBefore, playerDaoJDBC.findAllPlayers().size() - 1);
    }

    @Test
    void tryToCreateEqualsPlayers() {
        logger.debug("Execute test: tryToCreateEqualsPlayers()");
        assertNotNull(playerDaoJDBC);
        Player player = new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1);

        assertThrows(IllegalArgumentException.class, () -> {
            playerDaoJDBC.create(player);
            playerDaoJDBC.create(player);
        });
    }

    @Test
    void getPlayerById() {
        logger.debug("Execute test: getPlayerById()");
        List<Player> players = playerDaoJDBC.findAllPlayers();
        if (players.size() == 0) {
            playerDaoJDBC.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
            players = playerDaoJDBC.findAllPlayers();
        }

        Player playerSrc = players.get(0);
        Player playerDst = playerDaoJDBC.getPlayerById(playerSrc.getPlayerId());
        assertEquals(playerSrc.getFirstName(), playerDst.getFirstName());
    }

    @Test
    void updatePlayer() {
        logger.debug("Execute test: updatePlayer()");
        List<Player> players = playerDaoJDBC.findAllPlayers();
        if (players.size() == 0) {
            playerDaoJDBC.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
            players = playerDaoJDBC.findAllPlayers();
        }

        Player teamSrc = players.get(0);
        Player teamDst = playerDaoJDBC.getPlayerById(teamSrc.getPlayerId());
        assertEquals(teamSrc.getFirstName(), teamDst.getFirstName());
    }

    @Test
    void deletePlayer() {
        logger.debug("Execute test: deletePlayer()");
        playerDaoJDBC.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
        List<Player> players = playerDaoJDBC.findAllPlayers();

        playerDaoJDBC.delete(players.get(players.size() - 1).getPlayerId());
        assertEquals(players.size() - 1, playerDaoJDBC.findAllPlayers().size());
    }
}