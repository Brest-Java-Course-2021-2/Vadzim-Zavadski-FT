package com.zavadski.service.impl;

import com.zavadski.dao.PlayerDaoJDBCImpl;
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
import static org.mockito.ArgumentMatchers.any;

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

        Mockito.when(playerDao.getAllPlayers()).thenReturn(players);

        List<Player> allPlayers = playerService.getAllPlayers();

        assertNotNull(allPlayers);
        assertEquals(1, allPlayers.size());

        System.out.println(allPlayers);

        Mockito.verify(playerDao, Mockito.times(1)).getAllPlayers();
    }

    @Test
    void getAllPlayersIfEmpty() {

        List<Player> players = new ArrayList<>();

        Mockito.when(playerDao.getAllPlayers()).thenReturn(players);

        List<Player> allPlayers = playerService.getAllPlayers();

        assert(allPlayers.isEmpty());

        System.out.println(allPlayers);

        Mockito.verify(playerDao, Mockito.times(1)).getAllPlayers();
    }

//    @Test
//    void createNewPlayer() {
//
//        Player player = new Player();
//        Integer id = 1;
//
//        Mockito.when(playerDao.create(any(Player.class))).thenReturn(id);
//
//        Integer result = playerService.create(player);
//
//        assertNotNull(result);
//        assertEquals(1, result);
//
//        Mockito.verify(playerDao, Mockito.times(1)).create(any(Player.class));
//    }

}
