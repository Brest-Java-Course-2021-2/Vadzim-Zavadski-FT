package com.zavadski.dao;

import com.zavadski.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class PlayerDaoJDBCImplTest {

    @InjectMocks
    private PlayerDaoJDBCImpl playerDaoJDBC;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Player>> captorMapper;

    @Captor
    private ArgumentCaptor<SqlParameterSource> captorSource;

    @Captor
    private ArgumentCaptor<GeneratedKeyHolder> captorKeyHolder;

    @AfterEach
    public void check() {
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void findAllPlayers() {
        String sql = "select";
        ReflectionTestUtils.setField(playerDaoJDBC, "sqlGetAllPlayers", sql);
        Player player = new Player();
        List<Player> list = Collections.singletonList(player);

        Mockito.when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Player>>any())).thenReturn(list);

        List<Player> result = playerDaoJDBC.findAllPlayers();

        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql), captorMapper.capture());

        RowMapper<Player> mapper = captorMapper.getValue();

        Assertions.assertNotNull(mapper);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(player, result.get(0));
    }

    @Test
    public void getPlayerById() {
        String sql = "get";
        ReflectionTestUtils.setField(playerDaoJDBC, "sqlGetPlayerById", sql);
        int id = 0;
        Player player = new Player();

        Mockito.when(namedParameterJdbcTemplate.queryForObject(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<RowMapper<Player>>any())
        ).thenReturn(player);

        Player result = playerDaoJDBC.getPlayerById(id);

        Assertions.assertNotNull(result);
        Assertions.assertSame(player, result);

        Mockito.verify(namedParameterJdbcTemplate)
                .queryForObject(eq(sql), captorSource.capture(), captorMapper.capture());

        SqlParameterSource source = captorSource.getValue();
        RowMapper<Player> mapper = captorMapper.getValue();

        Assertions.assertNotNull(source);
        Assertions.assertNotNull(mapper);
    }

    //TODO тест нужно доработать
    @Test
    public void createNewPlayer() {

        String sql = "create";
//        ReflectionTestUtils.setField(playerDaoJDBC, "sqlCreatePlayer", sql);
        Player player = new Player("gjv", "df", LocalDate.parse("2018-01-01"),1);
        int id = 0;
        Integer count = 0;

        Mockito.when(namedParameterJdbcTemplate.update(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<KeyHolder>any())
        ).thenReturn(id);

        Integer result = playerDaoJDBC.create(player);

//        Mockito.verify(namedParameterJdbcTemplate)
//                .update(eq(sql), captorSource.capture(), captorKeyHolder.capture());
//
//        SqlParameterSource source = captorSource.getValue();
//        KeyHolder keyHolder = captorKeyHolder.getValue();
//
//        Assertions.assertNotNull(source);
//        Assertions.assertNotNull(keyHolder);
    }


    @Test
    public void deletePlayer() {
        String sql = "delete";
        ReflectionTestUtils.setField(playerDaoJDBC, "sqlDeletePlayerById", sql);
        int id = 0;
        Mockito.when(namedParameterJdbcTemplate.update(
                        any(),
                        ArgumentMatchers.<SqlParameterSource>any()))
                .thenReturn(0);

        int result = playerDaoJDBC.delete(id);

        Mockito.verify(namedParameterJdbcTemplate)
                .update(eq(sql), captorSource.capture());

        SqlParameterSource source = captorSource.getValue();
        Assertions.assertNotNull(source);
    }
}
