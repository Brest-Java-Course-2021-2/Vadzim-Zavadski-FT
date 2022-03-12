package com.zavadski.rest;

import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.Team;
import com.zavadski.rest.exception.CustomExceptionHandler;
import com.zavadski.service.TeamService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @Captor
    private ArgumentCaptor<Integer> captorId;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(teamController)
                .alwaysDo(MockMvcResultHandlers.print())
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    public void getAllTeams() throws Exception {

        Team team = new Team(1, "team");
        Team team2 = new Team(2, "team2");
        List<Team> teams = List.of(team, team2);

        Mockito.when(teamService.getAllTeams()).thenReturn(teams);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/teams"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].teamId", Matchers.is(teams.get(0).getTeamId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].teamName", Matchers.is(teams.get(0).getTeamName())));
        Mockito.verify(teamService, Mockito.times(1)).getAllTeams();

    }


    @Test
    public void getTeamById() throws Exception {

        Team team = new Team(1, "team");

        Mockito.when(teamService.getTeamById(anyInt())).thenReturn(team);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/teams/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teamId", Matchers.is(team.getTeamId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teamName", Matchers.is(team.getTeamName())));

        Mockito.verify(teamService, Mockito.times(1)).getTeamById(captorId.capture());

        Integer id = captorId.getValue();
        Assertions.assertEquals(1, id);
    }

    @Test
    public void getTeamByIdException() throws Exception {

        Mockito.when(teamService.getTeamById(anyInt()))
                .thenThrow(new UnacceptableName("test message"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/teams/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Handle: test message"));
    }

    @Test
    public void createTeam() throws Exception {

        Team team = new Team(1, "team");

        Mockito.when(teamService.create(team)).thenReturn(team.getTeamId());

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/teams"));
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teamId", Matchers.is(team.getTeamId())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.teamName", Matchers.is(team.getTeamName())));

//        Mockito.verify(teamService, Mockito.times(1)).getTeamById(captorId.capture());

//        Integer id = captorId.getValue();
//        Assertions.assertEquals(1, id);
    }

}
