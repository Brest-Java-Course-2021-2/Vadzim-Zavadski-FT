package com.zavadski.rest;

import com.zavadski.model.Player;
import com.zavadski.model.Team;
import com.zavadski.rest.exception.CustomExceptionHandler;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerService playerService;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController)
                .build();
    }

    @Test
    public void givenPlayers_whenGetPlayers_thenStatus200() throws Exception {

        Player player1 = new Player(1, "qqq", "www", LocalDate.parse("1992-01-01"), 2);
        Player player2 = new Player(2, "aaa", "sss", LocalDate.parse("2001-01-01"), 2);
        List<Player> players = List.of(player1, player2);

        Mockito.when(playerService.getAllPlayers()).thenReturn(players);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/players"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].playerId", Matchers.is(players.get(0).getPlayerId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is(players.get(0).getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", Matchers.is(players.get(0).getSurname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthday", Matchers.is(players.get(0).getBirthday().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].teamId", Matchers.is(players.get(0).getTeamId())));
        Mockito.verify(playerService, Mockito.times(1)).getAllPlayers();
    }
}
