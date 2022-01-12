package com.zavadski.service.impl;

import com.zavadski.dao.exception.TeamWithPlayerException;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import com.zavadski.service.config.ServiceTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:dao.properties"})
@Transactional
class TeamServiceImplIT {

    private final Logger logger = LogManager.getLogger(TeamServiceImplIT.class);

    @Autowired
    TeamService teamService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        logger.debug("Execute test: findAll()");
        assertNotNull(teamService);
        assertNotNull(teamService.findAllTeams());
    }

    @Test
    void shouldCount() {
        logger.debug("Execute test: shouldCount()");

        assertNotNull(teamService);
        Integer quantity = teamService.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertEquals(Integer.valueOf(3), quantity);
    }

    @Test
    void create() {
        logger.debug("Execute test: create()");

        assertNotNull(teamService);
        Integer teamsSizeBefore = teamService.count();
        assertNotNull(teamsSizeBefore);
        Team team = new Team("MU");
        Integer newTeamId =teamService.create(team);
        assertNotNull(newTeamId);
        assertEquals(teamsSizeBefore, teamService.count() - 1);
    }

    @Test
    void tryToCreateEqualsTeams() {
        logger.debug("Execute test: tryToCreateEqualsTeams()");

        assertNotNull(teamService);
        Team team = new Team("MU");

        assertThrows(IllegalArgumentException.class, () -> {
            teamService.create(team);
            teamService.create(team);
        });
    }

    @Test
    void getDepartmentById() {
        logger.debug("Execute test: getDepartmentById()");

        List<Team> teams = teamService.findAllTeams();
        if (teams.size() == 0) {
            teamService.create(new Team("TEST TEAM"));
            teams = teamService.findAllTeams();
        }

        Team teamSrc = teams.get(0);
        Team teamDst = teamService.getTeamById(teamSrc.getTeamId());
        assertEquals(teamSrc.getTeamName(), teamDst.getTeamName());
    }

    @Test
    void updateTeam(){
        logger.debug("Execute test: updateTeam()");

        List<Team> teams = teamService.findAllTeams();
        if (teams.size() == 0) {
            teamService.create(new Team("TEST TEAM"));
            teams = teamService.findAllTeams();
        }

        Team teamSrc = teams.get(0);
        Team teamDst = teamService.getTeamById(teamSrc.getTeamId());
        assertEquals(teamSrc.getTeamName(), teamDst.getTeamName());
    }

    @Test
    void tryToUpdateTeamWithSameName() {
        List<Team> teams = teamService.findAllTeams();
        assertThrows(DuplicateKeyException.class, () -> {
            Team team = teams.get(0);
            team.setTeamName("Lester");
            teamService.update(team);
        });
    }

    @Test
    void deleteTeam() {
        logger.debug("Execute test: deleteTeam()");
        teamService.create(new Team("TEST TEAM"));
        List<Team> teams = teamService.findAllTeams();

        teamService.delete(teams.get(teams.size() - 1).getTeamId());
        assertEquals(teams.size() - 1, teamService.findAllTeams().size());
    }

    @Test
    void tryDeleteTeamWithPlayer() {
        logger.debug("Execute test: deleteTeamWithPlayer()");
        List<Team> teamsBeforeDelete = teamService.findAllTeams();
        assertThrows(TeamWithPlayerException.class, () ->
                teamService.delete(teamsBeforeDelete.get(0).getTeamId()));
    }
}