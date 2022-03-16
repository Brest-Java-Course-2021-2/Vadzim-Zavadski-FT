package com.zavadski.rest.train;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavadski.model.Player;
import com.zavadski.rest.PlayerController;
import com.zavadski.rest.exception.CustomExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
public class PlayerControllerIT {

    public static final String PLAYERS_ENDPOINT = "/players";

    @Autowired
    private PlayerController playerController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    MockMvcPlayerService playerService = new MockMvcPlayerService();

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void shouldFindAllPlayers() throws Exception {

        Player player = new Player(1, "qqq", "www", LocalDate.parse("1992-01-01"), 2);
        Integer id = playerService.create(player);

        List<Player> players = playerService.getAllPlayers();

        assertNotNull(players);
        System.out.println(players);
        assertTrue(players.size() > 0);
    }

    class MockMvcPlayerService {

        public List<Player> getAllPlayers() throws Exception {

            MockHttpServletResponse response = mockMvc.perform(get(PLAYERS_ENDPOINT)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Player>>() {
            });
        }

        public Integer create(Player player) throws Exception {

            String json = objectMapper.writeValueAsString(player);
            MockHttpServletResponse response =
                    mockMvc.perform(post(PLAYERS_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}
