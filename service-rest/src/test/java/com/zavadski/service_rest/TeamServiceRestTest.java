package com.zavadski.service_rest;

import com.zavadski.model.Team;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.zavadski.model.constants.TeamConstants.TEAM_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(locations = {"classpath:app-context-test.xml"})
    public class TeamServiceRestTest {

        private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceRestTest.class);

        public static final String TEAMS_URL = "http://localhost:8088/teams";

        @Autowired
        RestTemplate restTemplate;

        private MockRestServiceServer mockServer;

        private ObjectMapper mapper = new ObjectMapper();

        TeamServiceRest teamService;

        @BeforeEach
        public void before() {
            mockServer = MockRestServiceServer.createServer(restTemplate);
           teamService = new TeamServiceRest(TEAMS_URL, restTemplate);
        }

        @Test
        public void shouldFindAllTeams() throws Exception {

            LOGGER.debug("shouldFindAllTeams()");
            // given
            mockServer.expect(ExpectedCount.once(), requestTo(new URI(TEAMS_URL)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                    );

            // when
            List<Team> teams = teamService.findAllTeams();

            // then
            mockServer.verify();
            assertNotNull(teams);
            assertTrue(teams.size() > 0);
        }

        @Test
        public void shouldCreateTeam() throws Exception {

            LOGGER.debug("shouldCreateTeam()");
            // given
            Team team = new Team()
                    .setTeamName(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));

            mockServer.expect(ExpectedCount.once(), requestTo(new URI(TEAMS_URL)))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString("1"))
                    );
            // when
            Integer id = teamService.create(team);

            // then
            mockServer.verify();
            assertNotNull(id);
        }

        @Test
        public void shouldFindTeamById() throws Exception {

            // given
            Integer id = 1;
            Team team = new Team()
                    .setTeamId(id)
                    .setTeamName(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));

            mockServer.expect(ExpectedCount.once(), requestTo(new URI(TEAMS_URL + "/" + id)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(team))
                    );

            // when
           Team resultTeam = teamService.getTeamById(id);

            // then
            mockServer.verify();
            assertNotNull(resultTeam);
            assertEquals(resultTeam.getTeamId(), id);
            assertEquals(resultTeam.getTeamName(), team.getTeamName());
        }

        @Test
        public void shouldUpdateTeam() throws Exception {

            // given
            Integer id = 1;
           Team team = new Team()
                    .setTeamId(id)
                    .setTeamName(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));

            mockServer.expect(ExpectedCount.once(), requestTo(new URI(TEAMS_URL)))
                    .andExpect(method(HttpMethod.PUT))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString("1"))
                    );

            mockServer.expect(ExpectedCount.once(), requestTo(new URI(TEAMS_URL + "/" + id)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(team))
                    );

            // when
            int result = teamService.update(team);
            Team updatedTeam = teamService.getTeamById(id);

            // then
            mockServer.verify();
            assertTrue(1 == result);

            assertNotNull(updatedTeam);
            assertEquals(updatedTeam.getTeamId(), id);
            assertEquals(updatedTeam.getTeamName(), team.getTeamName());
        }

        @Test
        public void shouldDeleteTeam() throws Exception {

            // given
            Integer id = 1;
            mockServer.expect(ExpectedCount.once(), requestTo(new URI(TEAMS_URL + "/" + id)))
                    .andExpect(method(HttpMethod.DELETE))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString("1"))
                    );
            // when
            int result = teamService.delete(id);

            // then
            mockServer.verify();
            assertTrue(1 == result);
        }

        private Team create(int index) {
           Team team = new Team();
         team.setTeamId(index);
           team.setTeamName("d" + index);
            return team;
        }
    }
