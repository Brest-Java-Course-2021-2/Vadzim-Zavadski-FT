package com.zavadski.dao;

import com.zavadski.dao.exception.PlayerWrongFilterDate;
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

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({PlayerDtoDaoJdbc.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class PlayerDtoDaoJdbcIT {

    private final Logger logger = LogManager.getLogger(PlayerDtoDaoJdbcIT.class);

    private PlayerDtoDaoJdbc playerDtoDaoJdbc;

    public PlayerDtoDaoJdbcIT(@Autowired PlayerDtoDao playerDtoDaoJdbc) {
        this.playerDtoDaoJdbc = (PlayerDtoDaoJdbc) playerDtoDaoJdbc;
    }

    @Test
    public void filterByBirthday() {

        logger.debug("Execute test: filterByBirthday()");

        assertNotNull(playerDtoDaoJdbc);
        assertNotNull(playerDtoDaoJdbc.filterByBirthday(LocalDate.parse("2000-01-01"), LocalDate.parse("2010-01-01")));
        assertThrows(PlayerWrongFilterDate.class, () -> {
            playerDtoDaoJdbc.filterByBirthday(LocalDate.parse("2020-01-01"), LocalDate.parse("2010-01-01"));
        });
    }
}
