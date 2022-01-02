package com.zavadski.dao;

import com.zavadski.dao.exception.PlayerWrongFilterDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@Rollback
class PlayerDtoDaoJdbcIT {

    private final Logger logger = LogManager.getLogger(PlayerDtoDaoJdbcIT.class);

    private PlayerDtoDaoJdbc playerDtoDaoJdbc;

    public PlayerDtoDaoJdbcIT(@Autowired PlayerDtoDao playerDtoDaoJdbc) {
        this.playerDtoDaoJdbc = (PlayerDtoDaoJdbc) playerDtoDaoJdbc;
    }

    //TODO тест можно доработать
    @Test
    public void filterByBirthday() {
        logger.debug("PlayerDtoDaoJdbcIT test: filterByBirthday()");
        assertNotNull(playerDtoDaoJdbc);
        assertNotNull(playerDtoDaoJdbc.filterByBirthday(LocalDate.parse("1990-01-01"), LocalDate.parse("2010-01-01")));
        assertNotNull(playerDtoDaoJdbc.filterByBirthday(LocalDate.parse("1990-01-01"), null));
        assertNotNull(playerDtoDaoJdbc.filterByBirthday(null, LocalDate.parse("1991-01-01")));
        assertThrows(PlayerWrongFilterDate.class, () ->
                playerDtoDaoJdbc.filterByBirthday(LocalDate.parse("2020-01-01"), LocalDate.parse("2010-01-01")));
    }
}
