package com.zavadski.web_app;

import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.zavadski.model.constants.TeamConstants.TEAM_NAME_SIZE;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
@Transactional
class TeamControllerIT {

    @Autowired
    private WebApplicationContext wac;

        @Autowired
    private TeamService teamService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void shouldReturnTeamsPage() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/teams")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("teams"))
                .andExpect(model().attribute("teams", hasItem(
                        allOf(
                                hasProperty("teamId", is(1)),
                                hasProperty("teamName", is("Liverpool")),
                                hasProperty("numberOfPlayers", is(2))
                        )
                )))
                .andExpect(model().attribute("teams", hasItem(
                        allOf(
                                hasProperty("teamId", is(2)),
                                hasProperty("teamName", is("Arsenal")),
                                hasProperty("numberOfPlayers", is(3))
                        )
                )))
                .andExpect(model().attribute("teams", hasItem(
                        allOf(
                                hasProperty("teamId", is(3)),
                                hasProperty("teamName", is("Lester")),
                                hasProperty("numberOfPlayers", is(0))
                        )
                )));
    }

    @Test
    void shouldAddTeam() throws Exception {
        // WHEN
        assertNotNull(teamService);
        Integer teamsSizeBefore = teamService.count();
        assertNotNull(teamsSizeBefore);
        Team team = new Team("MU");

        // THEN
        //Integer newTeamId = teamService.create(team);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/team")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("teamName", team.getTeamName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/teams"))
                .andExpect(redirectedUrl("/teams"));

        // VERIFY
        assertEquals(teamsSizeBefore, teamService.count() - 1);
    }

    @Test
    public void shouldOpenEditTeamPageById() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/team/1")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("team"))
                .andExpect(model().attribute("isNew", is(false)))
                .andExpect(model().attribute("team", hasProperty("teamId", is(1))))
                .andExpect(model().attribute("team", hasProperty("teamName", is("Liverpool"))));
    }

    @Test
    public void shouldUpdateTeamAfterEdit() throws Exception {

        String testName = RandomStringUtils.randomAlphabetic(TEAM_NAME_SIZE);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/team/1")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("teamId", "1")
                                .param("teamName", testName)
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/teams"))
                .andExpect(redirectedUrl("/teams"));

        Team team = teamService.getTeamById(1);
        assertNotNull(team);
        assertEquals(testName, team.getTeamName());
    }

    @Test
    public void shouldDeleteTeam() throws Exception {

        Integer countBefore = teamService.count();

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/team/3/delete")
                ).andExpect(status().isFound())
                .andExpect(view().name("redirect:/teams"))
                .andExpect(redirectedUrl("/teams"));

        // verify database size
        Integer countAfter = teamService.count();
        Assertions.assertEquals(countBefore - 1, countAfter);
    }
}