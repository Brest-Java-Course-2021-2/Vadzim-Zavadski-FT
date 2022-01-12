package com.zavadski.service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.config.ServiceRestTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@Import({ServiceRestTestConfig.class})
class TeamDtoServiceRestTest {

    private final Logger logger = LogManager.getLogger(TeamDtoServiceRestTest.class);

    public static final String URL = "http://localhost:8088/team_dtos";

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    TeamDtoServiceRest teamDtoServiceRest;

    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        teamDtoServiceRest = new TeamDtoServiceRest(URL, restTemplate);
    }

    @Test
    void findAllWithNumberOfPlayers() throws Exception {

        logger.debug("shouldFindAllTeams()");
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );

        // when
        List<TeamDto> list = teamDtoServiceRest.findAllWithNumberOfPlayers();

        // then
        mockServer.verify();
        assertNotNull(list);
        assertTrue(list.size() > 0);

    }

    private TeamDto create(int index) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamId(index);
        teamDto.setTeamName("d" + index);
        teamDto.setNumberOfPlayers(Integer.valueOf(100 + index));
        return teamDto;
    }
}
