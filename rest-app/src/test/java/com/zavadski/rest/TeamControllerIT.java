package com.zavadski.rest;

import com.zavadski.model.Team;
import com.zavadski.rest.exception.CustomExceptionHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavadski.rest.exception.ErrorResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.zavadski.model.constants.TeamConstants.TEAM_NAME_SIZE;
import static com.zavadski.rest.exception.CustomExceptionHandler.TEAM_NOT_FOUND;
import static com.zavadski.rest.exception.CustomExceptionHandler.VALIDATION_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
public class TeamControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamControllerIT.class);

    public static final String TEAMS_ENDPOINT = "/teams";

    @Autowired
    private TeamController teamController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    MockMvcTeamService teamService = new MockMvcTeamService();

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(teamController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void shouldFindAllTeams() throws Exception {

        // given
        Team team = new Team(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));
        Integer id = teamService.create(team);

        // when
        List<Team> teams = teamService.findAll();

        // then
        assertNotNull(teams);
        assertTrue(teams.size() > 0);
    }

    @Test
    public void shouldCreateTeam() throws Exception {
        Team team = new Team(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));
        Integer id = teamService.create(team);
        assertNotNull(id);
    }

    @Test
    public void shouldFindTeamById() throws Exception {

        // given
        Team team = new Team(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));
        Integer id = teamService.create(team);

        assertNotNull(id);

        // when
        Optional<Team> optionalTeam = teamService.findById(id);

        // then
        assertTrue(optionalTeam.isPresent());
        assertEquals(optionalTeam.get().getTeamId(), id);
        assertEquals(team.getTeamName(), optionalTeam.get().getTeamName());
    }

    @Test
    public void shouldUpdateTeam() throws Exception {

        // given
        Team team = new Team(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));
        Integer id = teamService.create(team);
        assertNotNull(id);

        Optional<Team> teamOptional = teamService.findById(id);
        assertTrue(teamOptional.isPresent());

        teamOptional.get().
               setTeamName(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));

        // when
        int result = teamService.update(teamOptional.get());

        // then
        assertTrue(1 == result);

        Optional<Team> updatedTeamOptional = teamService.findById(id);
        assertTrue(updatedTeamOptional.isPresent());
        assertEquals(updatedTeamOptional.get().getTeamId(), id);
        assertEquals(updatedTeamOptional.get().getTeamName(), teamOptional.get().getTeamName());

    }

    @Test
    public void shouldDeleteDepartment() throws Exception {
        // given
        Team team = new Team(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));
        Integer id = teamService.create(team);

        List<Team> teams = teamService.findAll();
        assertNotNull(teams);

        // when
        int result = teamService.delete(id);

        // then
        assertTrue(1 == result);

        List<Team> currentTeams =teamService.findAll();
        assertNotNull(currentTeams);

        assertTrue(teams.size() - 1 == currentTeams.size());
    }

    @Test
    public void shouldFailOnCreateTeamWithDuplicateName() throws Exception {
        Team team1 = new Team(RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE));
        Integer id = teamService.create(team1);
        assertNotNull(id);

        Team team2 = new Team(team1.getTeamName());

        MockHttpServletResponse response =
                mockMvc.perform(post(TEAMS_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(team2))
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isUnprocessableEntity())
                        .andReturn().getResponse();

        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), VALIDATION_ERROR);
    }

    class MockMvcTeamService {

        public List<Team> findAll() throws Exception {
            LOGGER.debug("findAll()");
            MockHttpServletResponse response = mockMvc.perform(get(TEAMS_ENDPOINT)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Team>>() {
            });
        }

        public Optional<Team> findById(Integer id) throws Exception {

            LOGGER.debug("findById({})", id);
            MockHttpServletResponse response = mockMvc.perform(get(TEAMS_ENDPOINT + "/" + id)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Team.class));
        }

        public Integer create(Team team) throws Exception {

            LOGGER.debug("create({})", team);
            String json = objectMapper.writeValueAsString(team);
            MockHttpServletResponse response =
                    mockMvc.perform(post(TEAMS_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        private int update(Team team) throws Exception {

            LOGGER.debug("update({})", team);
            MockHttpServletResponse response =
                    mockMvc.perform(put(TEAMS_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(team))
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        private int delete(Integer teamId) throws Exception {

            LOGGER.debug("delete(id:{})", teamId);
            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.delete(new StringBuilder(TEAMS_ENDPOINT).append("/")
                                            .append(teamId).toString())
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();

            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}
