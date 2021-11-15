package com.zavadski.web_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
@Transactional
class TeamControllerIT {

    @Autowired
    private WebApplicationContext wac;

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
                .andExpect(MockMvcResultMatchers.status().isOk())
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
}