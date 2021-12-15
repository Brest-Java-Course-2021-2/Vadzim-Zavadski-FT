package com.zavadski.dao;

import com.zavadski.model.dto.TeamDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@Rollback
class TeamDtoDaoJdbcIT {

    private final Logger logger = LogManager.getLogger(TeamDtoDaoJdbcIT.class);

    private TeamDtoDaoJdbc teamDtoDaoJdbc;

    public TeamDtoDaoJdbcIT(@Autowired TeamDtoDao teamDtoDaoJdbc) {
        this.teamDtoDaoJdbc = (TeamDtoDaoJdbc) teamDtoDaoJdbc;
    }

    @Test
    public void findAllWithNumberOfPlayers() {
        logger.debug("Execute test: findAll()");
        assertNotNull(teamDtoDaoJdbc);
        assertNotNull(teamDtoDaoJdbc.findAllWithNumberOfPlayers());
    }
}
