package com.zavadski.dao;

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

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({TeamDtoDaoJdbc.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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

        logger.debug("Execute test: findAllWithNumberOfPlayers");

        assertNotNull(teamDtoDaoJdbc);
        assertNotNull(teamDtoDaoJdbc.findAllWithNumberOfPlayers());
    }
}
