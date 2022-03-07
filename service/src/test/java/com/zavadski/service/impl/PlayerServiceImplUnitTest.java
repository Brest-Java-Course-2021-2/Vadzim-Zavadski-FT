package com.zavadski.service.impl;

import com.zavadski.dao.PlayerDao;
import com.zavadski.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplUnitTest {

    @Mock
    private PlayerDao playerDao;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllPlayers() {
    }

    @Test
    void getPlayerById() {
    }

    @Test
    void create() {

        Player player = new Player("Tim", "Tom", LocalDate.parse("2000-01-01"), 1);

        Mockito.when(playerDao.create(Mockito.any(Player.class))).thenReturn(player.getPlayerId());

        Integer newPlayerId = playerDao.create(player);

        assertEquals(0, newPlayerId);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
