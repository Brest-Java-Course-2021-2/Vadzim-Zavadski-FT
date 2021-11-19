package com.zavadski.service.impl;

import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class TeamServiceImplIT {

    @Autowired
    TeamService teamService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCount() {
        assertNotNull(teamService);
        Integer quantity = teamService.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertEquals(Integer.valueOf(3), quantity);
    }

    @Test
    void create() {
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
        assertNotNull(teamService);
        Team team = new Team("MU");

        assertThrows(IllegalArgumentException.class, () -> {
            teamService.create(team);
            teamService.create(team);
        });
    }
}