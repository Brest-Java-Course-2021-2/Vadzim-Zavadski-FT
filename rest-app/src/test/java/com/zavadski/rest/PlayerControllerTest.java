package com.zavadski.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController)
                .build();
    }

    @Test
    public void getAllPlayers() throws Exception {

        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "qqq", "www", LocalDate.parse("1992-01-01"), 2));
        players.add(new Player(2, "aaa", "sss", LocalDate.parse("2001-01-01"), 2));

        Mockito.when(playerService.getAllPlayers()).thenReturn(players);

        String url = "/players";

        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse = objectMapper.writeValueAsString(players);

        assert(actualJsonResponse).contentEquals(expectedJsonResponse);

        Mockito.verify(playerService, Mockito.times(1)).getAllPlayers();
    }
}
