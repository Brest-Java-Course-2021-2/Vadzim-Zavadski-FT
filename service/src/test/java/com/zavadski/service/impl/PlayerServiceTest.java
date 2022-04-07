package com.zavadski.service.impl;

import com.zavadski.dao.jdbc.PlayerDaoJDBCImpl;
import com.zavadski.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerDaoJDBCImpl playerDao;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private final Player player = new Player();

    @DisplayName("JUnit test for getAllPlayers method")
    @BeforeEach
    public void setup() {
        player.setPlayerId(1);
        player.setFirstName("Tomas");
    }

    @Test
    void testGetAllPlayers_thenReturnPlayersList() {

        List<Player> players = new ArrayList<>();
        players.add(player);

        when(playerDao.getAllPlayers()).thenReturn(players);

        List<Player> playerList = playerService.getAllPlayers();

        assertNotNull(playerList);
        assertEquals(1, playerList.size());
        assertSame(players, playerList);

        verify(playerDao, times(1)).getAllPlayers();
    }

    @Test
    void testGetAllPlayers_thenReturnEmptyPlayersList() {

        when(playerDao.getAllPlayers()).thenReturn(Collections.emptyList());

        List<Player> playerList = playerService.getAllPlayers();

        assert (playerList).isEmpty();

        verify(playerDao, times(1)).getAllPlayers();

    }

    @Test
    void testGetAllPlayers_thenReturnException() {

        when(playerDao.getAllPlayers()).thenThrow(new RuntimeException("Some expected message"));

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> playerService.getAllPlayers());

        verify(playerDao, times(1)).getAllPlayers();

        assertEquals("Some expected message", thrown.getMessage());
    }
}
