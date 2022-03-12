package com.zavadski.dao;

import com.zavadski.dao.exception.TeamWithPlayerException;
import com.zavadski.model.Team;
import com.zavadski.testdb.SpringJdbcConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({TeamDaoJDBCImpl.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
    void getDepartmentById() {
        List<Team> teams = teamDaoJDBC.findAll();
        if (teams.size() == 0) {
            teamDaoJDBC.create(new Team("TEST TEAM"));
            teams = teamDaoJDBC.findAll();
        }

        Team teamSrc = teams.get(0);
        Team teamDst = teamDaoJDBC.getTeamById(teamSrc.getTeamId());
        assertEquals(teamSrc.getTeamName(), teamDst.getTeamName());
    }

    @Test
    void updateTeam() {
        List<Team> teams = teamDaoJDBC.findAll();
        if (teams.size() == 0) {
            teamDaoJDBC.create(new Team("TEST TEAM"));
            teams = teamDaoJDBC.findAll();
        }
        Team teamSrc = teams.get(0);
        Team teamDst = teamDaoJDBC.getTeamById(teamSrc.getTeamId());
        assertEquals(teamSrc.getTeamName(), teamDst.getTeamName());
    }

    @Test
    void tryToUpdateTeamWithSameName() {
        List<Team> teams = teamDaoJDBC.findAll();
        assertThrows(DuplicateKeyException.class, () -> {
            Team team = teams.get(0);
            team.setTeamName("Lester");
            teamDaoJDBC.update(team);
        });
    }

    @Test
    void deleteTeam() {
        teamDaoJDBC.create(new Team("TEST TEAM"));
        List<Team> teams = teamDaoJDBC.findAll();

        teamDaoJDBC.delete(teams.get(teams.size() - 1).getTeamId());
        assertEquals(teams.size() - 1, teamDaoJDBC.findAll().size());
    }

    @Test
    void tryDeleteTeamWithPlayer() {
        List<Team> teamsBeforeDelete = teamDaoJDBC.findAll();
        assertThrows(TeamWithPlayerException.class, () ->
                teamDaoJDBC.delete(teamsBeforeDelete.get(0).getTeamId()));
    }

    //todo зависит от начальных данных
    @Test
    void shouldCount() {
        assertNotNull(teamDaoJDBC);
        Integer quantity = teamDaoJDBC.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertEquals(Integer.valueOf(3), quantity);
    }
}
