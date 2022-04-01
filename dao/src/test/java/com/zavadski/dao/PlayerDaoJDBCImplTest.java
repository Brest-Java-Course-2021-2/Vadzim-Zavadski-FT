package com.zavadski.dao;

import com.zavadski.dao.jdbc.PlayerDaoJDBCImpl;
import com.zavadski.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerDaoJDBCImplTest {

    @InjectMocks
    private PlayerDaoJDBCImpl playerDaoJDBC;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Player>> captorMapper;

    @AfterEach
    public void check() {
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void getAllPlayers() {

        String sql = "select";
        ReflectionTestUtils.setField(playerDaoJDBC, "sqlGetAllPlayers", sql);

        List<Player> players = new ArrayList<>();
        Player testPlayer = new Player();
        players.add(testPlayer);

        when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Player>>any())).thenReturn(players);

        List<Player> result = playerDaoJDBC.getAllPlayers();

        verify(namedParameterJdbcTemplate).query(eq(sql), captorMapper.capture());

        RowMapper<Player> mapper = captorMapper.getValue();

        assertNotNull(mapper);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertSame(testPlayer, result.get(0));
    }
}
