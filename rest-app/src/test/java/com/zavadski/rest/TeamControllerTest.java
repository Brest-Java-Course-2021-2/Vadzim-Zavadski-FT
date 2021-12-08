package com.zavadski.rest;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;

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
    public void getTeamById() throws Exception {
        Team team = new Team();
        team.setTeamId(7);
        team.setTeamName("name");

        Mockito.when(teamService.getTeamById(anyInt())).thenReturn(team);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/teams/8")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teamId", Matchers.is(team.getTeamId())))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.teamName", Matchers.is(team.getTeamName())))
        ;

        Mockito.verify(teamService).getTeamById(captorId.capture());

        Integer id = captorId.getValue();
        Assertions.assertEquals(8, id);
    }

    @Test
    public void getTeamByIdException() throws Exception {

        Mockito.when(teamService.getTeamById(anyInt()))
                .thenThrow(new IllegalArgumentException("test message"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/teams/8")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Handle: test message"))
        ;
    }
}