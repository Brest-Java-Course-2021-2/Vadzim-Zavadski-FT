package com.zavadski.service.impl.train;

import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.TeamDtoService;
import com.zavadski.service.config.ServiceTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Import({ServiceTestConfig.class})
@PropertySource({"classpath:dao.properties"})
@Transactional
class TeamDtoServiceImplIT {

    private final Logger logger = LogManager.getLogger(TeamDtoServiceImplIT.class);

    @Autowired
    TeamDtoService teamDtoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldFindAllWithNumberOfPlayers() {

        logger.debug("Execute test: shouldFindAllWithNumberOfPlayers()");

        List<TeamDto> teams = teamDtoService.findAllWithNumberOfPlayers();
        assertNotNull(teams);
        assertTrue(teams.size() > 0);
        assertTrue(teams.get(0).getNumberOfPlayers() > 0);
    }
}