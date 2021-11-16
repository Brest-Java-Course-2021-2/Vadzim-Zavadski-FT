package com.zavadski.dao;

import com.zavadski.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@Rollback
class TeamDaoJDBCImplIT {

    private final Logger logger = LogManager.getLogger(TeamDaoJDBCImplIT.class);

    private TeamDaoJDBCImpl teamDaoJDBC;

    public TeamDaoJDBCImplIT(@Autowired TeamDao teamDaoJDBC) {
        this.teamDaoJDBC = (TeamDaoJDBCImpl) teamDaoJDBC;
    }

    @Test
    void findAll() {
        logger.debug("Execute test: findAll()");
        assertNotNull(teamDaoJDBC);
        assertNotNull(teamDaoJDBC.findAll());
    }

    @Test
    void create() {
        assertNotNull(teamDaoJDBC);
        int teamSizeBefore = teamDaoJDBC.count();
        Team team = new Team("MU");
        Integer newTeamId = teamDaoJDBC.create(team);
        assertNotNull(newTeamId);
        assertEquals((int) teamSizeBefore, teamDaoJDBC.count() - 1);
    }

    @Test
    void tryToCreateEqualsTeams() {
        assertNotNull(teamDaoJDBC);
        Team team = new Team("MU");

        assertThrows(IllegalArgumentException.class, () -> {
            teamDaoJDBC.create(team);
            teamDaoJDBC.create(team);
        });
    }
    @Test
    void shouldCount() {
        assertNotNull(teamDaoJDBC);
        Integer quantity = teamDaoJDBC.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertEquals(Integer.valueOf(3), quantity);
    }
}