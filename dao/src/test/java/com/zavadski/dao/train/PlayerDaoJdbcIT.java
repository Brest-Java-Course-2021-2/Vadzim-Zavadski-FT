package com.zavadski.dao.train;

import com.zavadski.dao.PlayerDao;
import com.zavadski.dao.PlayerDaoJDBCImpl;
import com.zavadski.model.Player;
import com.zavadski.testdb.SpringJdbcConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({PlayerDaoJDBCImpl.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
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
        assertNotNull(playerDaoJDBC.getAllPlayers());
    }

    @Test
    void create() {

        logger.debug("Execute test: createPlayer()");

        assertNotNull(playerDaoJDBC);
        int teamSizeBefore = playerDaoJDBC.getAllPlayers().size();
        Player player = new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1);
        Integer newPlayerId = playerDaoJDBC.create(player);
        System.out.println(newPlayerId);
        assertNotNull(newPlayerId);
        assertEquals(teamSizeBefore, playerDaoJDBC.getAllPlayers().size() - 1);
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

        List<Player> players = playerDaoJDBC.getAllPlayers();
        if (players.size() == 0) {
            playerDaoJDBC.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
            players = playerDaoJDBC.getAllPlayers();
        }

        Player playerSrc = players.get(0);
        Player playerDst = playerDaoJDBC.getPlayerById(playerSrc.getPlayerId());
        assertEquals(playerSrc.getFirstName(), playerDst.getFirstName());
    }

    @Test
    void updatePlayer() {

        logger.debug("Execute test: updatePlayer()");

        List<Player> players = playerDaoJDBC.getAllPlayers();
        if (players.size() == 0) {
            playerDaoJDBC.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
            players = playerDaoJDBC.getAllPlayers();
        }

        Player teamSrc = players.get(0);
        Player teamDst = playerDaoJDBC.getPlayerById(teamSrc.getPlayerId());
        assertEquals(teamSrc.getFirstName(), teamDst.getFirstName());
    }

    @Test
    void deletePlayer() {

        logger.debug("Execute test: deletePlayer()");

        playerDaoJDBC.create(new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1));
        List<Player> players = playerDaoJDBC.getAllPlayers();

        playerDaoJDBC.delete(players.get(players.size() - 1).getPlayerId());
        assertEquals(players.size() - 1, playerDaoJDBC.getAllPlayers().size());
    }
}
