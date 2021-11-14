package com.zavadski.dao;

import com.zavadski.model.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
class TeamDaoJDBCImplIT {

    private TeamDaoJDBCImpl teamDaoJDBC;

    public TeamDaoJDBCImplIT(@Autowired TeamDao teamDaoJDBC) {
        this.teamDaoJDBC = (TeamDaoJDBCImpl) teamDaoJDBC;
    }

    @Test
    void findAll() {
        assertNotNull(teamDaoJDBC);
        assertNotNull(teamDaoJDBC.findAll());
    }

    @Test
    void create() {
        assertNotNull(teamDaoJDBC);
        int teamSizeBefore = teamDaoJDBC.findAll().size();
        Team team = new Team("Arsenal 2");
        Integer newTeamId = teamDaoJDBC.create(team);
        assertNotNull(newTeamId);
        assertEquals((int) teamSizeBefore, teamDaoJDBC.findAll().size() - 1);
    }

    @Test
    void tryToCreateEqualsTeams() {
        assertNotNull(teamDaoJDBC);
        Team team = new Team("Lester");

        assertThrows(DuplicateKeyException.class, () -> {
            teamDaoJDBC.create(team);
            teamDaoJDBC.create(team);
        });
    }
}