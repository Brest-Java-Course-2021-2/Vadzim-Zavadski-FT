package com.zavadski.service.impl;

import com.zavadski.service.PlayerDtoService;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class PlayerDtoServiceImplIT {

    private final Logger logger = LogManager.getLogger(PlayerServiceImplIT.class);

    @Autowired
    PlayerDtoService playerDtoService;

    //TODO тест нужно доработать
    @Test
    public void filterByBirthday() {
        logger.debug("Execute test: filterByBirthday()");
        assertNotNull(playerDtoService);
        assertNotNull(playerDtoService.filterByBirthday(LocalDate.parse("1990-01-01"), LocalDate.parse("2010-01-01")));
    }
}