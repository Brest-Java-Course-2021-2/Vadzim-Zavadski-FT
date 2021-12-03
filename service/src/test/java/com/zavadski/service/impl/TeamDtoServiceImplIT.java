package com.zavadski.service.impl;

import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.TeamDtoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class TeamDtoServiceImplIT {

    @Autowired
    TeamDtoService teamDtoService;

//    @Test
//    public void shouldFindAllWithNumberOfPlayers() {
//        List<TeamDto> teams = teamDtoService.findAllWithNumberOfPlayers();
//        assertNotNull(teams);
//        assertTrue(teams.size() > 0);
//        assertTrue(teams.get(0).getNumberOfPlayers().intValue() > 0);
//    }
}