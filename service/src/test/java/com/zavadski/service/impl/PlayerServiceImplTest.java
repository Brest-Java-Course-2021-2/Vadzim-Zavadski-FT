package com.zavadski.service.impl;

import com.zavadski.dao.jdbc.PlayerDaoJDBCImpl;
import com.zavadski.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerDaoJDBCImpl playerDao;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void getAllPlayers() {

        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "Edvin", "Win", LocalDate.of(1992, 01, 21), 1));

        when(playerDao.getAllPlayers()).thenReturn(players);

        List<Player> allPlayers = playerService.getAllPlayers();

        assertNotNull(allPlayers);
        assertEquals(1, allPlayers.size());
        assertSame(players, allPlayers);

        verify(playerDao, Mockito.times(1)).getAllPlayers();
    }
}
