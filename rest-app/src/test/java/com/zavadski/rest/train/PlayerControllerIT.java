package com.zavadski.rest.train;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavadski.model.Player;
import com.zavadski.rest.PlayerController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
class PlayerControllerIT {

    @Autowired
    private PlayerController playerController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPlayers() throws Exception {
        // given
        Player player = new Player("Tom", "Tttt", LocalDate.of(1992, 2, 1), 1);

        mockMvc.perform(get("/players")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper
                        .writeValueAsString(List.of(player))));

    }

//    private Player createTestPlayer(String firstName, String surname, LocalDate birthday, Integer teamId) {
//        Player player = new Player(firstName, surname, birthday, teamId);
//        return p.save(emp);
//    }
}