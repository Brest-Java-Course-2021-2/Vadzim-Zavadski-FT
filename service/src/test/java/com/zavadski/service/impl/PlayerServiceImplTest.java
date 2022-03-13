package com.zavadski.service.impl;

import com.zavadski.dao.PlayerDaoJDBCImpl;
import com.zavadski.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerDaoJDBCImpl playerDao;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void createNewPlayer() {

        Player player = new Player();
        Integer id = 1;

        Mockito.when(playerDao.create(any(Player.class))).thenReturn(id);

        Integer result = playerService.create(player);

        assertNotNull(result);
        assertEquals(1, result);

        Mockito.verify(playerDao, Mockito.times(1)).create(any(Player.class));
    }

    @Test
    void createNewPlayerError() {

        Player player = new Player();
        Integer id = 1;

        Mockito.when(playerDao.create(any(Player.class))).thenReturn(id);

        Integer result = playerService.create(player);

        assertNotNull(result);
        assertEquals(1, result);

        Mockito.verify(playerDao, Mockito.times(1)).create(any(Player.class));
    }
}
