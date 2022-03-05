package com.zavadski.service.impl;

import com.zavadski.dao.exception.PlayerWrongFilterDate;
import com.zavadski.service.PlayerDtoService;
import com.zavadski.service.config.ServiceTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:dao.properties"})
@Transactional
class PlayerDtoServiceImplIT {

    private final Logger logger = LogManager.getLogger(PlayerServiceImplIT.class);

    @Autowired
    PlayerDtoService playerDtoService;

    @Test
    public void filterByBirthday() {

        logger.debug("Execute test: filterByBirthday()");
        assertNotNull(playerDtoService);
        assertNotNull(playerDtoService.filterByBirthday(LocalDate.parse("2000-01-01"), LocalDate.parse("2010-01-01")));
    }

    @Test
    public void wrongFilterByBirthday() {

        logger.debug("Execute test: filterByBirthday()");

        assertThrows(PlayerWrongFilterDate.class, () -> {
            playerDtoService.filterByBirthday(LocalDate.parse("2020-01-01"), LocalDate.parse("2010-01-01"));
        });
    }
}
