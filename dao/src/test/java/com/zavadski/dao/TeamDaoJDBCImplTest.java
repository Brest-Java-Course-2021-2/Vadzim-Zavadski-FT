package com.zavadski.dao;

import com.zavadski.model.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class TeamDaoJDBCImplTest {

    @InjectMocks
    private TeamDaoJDBCImpl teamDaoJDBC;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Team>> captorMapper;

    @Captor
    private ArgumentCaptor<SqlParameterSource> captorSource;

    @AfterEach
    public void check() {
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void findAll() {
        String sql = "select";
        ReflectionTestUtils.setField(teamDaoJDBC, "sqlGetAllTeams", sql);
        Team team = new Team();
        List<Team> list = Collections.singletonList(team);

        Mockito.when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Team>>any())).thenReturn(list);

        List<Team> result = teamDaoJDBC.findAll();

        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql), captorMapper.capture());

        RowMapper<Team> mapper = captorMapper.getValue();

        Assertions.assertNotNull(mapper);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(team, result.get(0));
    }

    @Test
    public void getTeamById() {
        String sql = "get";
        ReflectionTestUtils.setField(teamDaoJDBC, "sqlGetTeamById", sql);
        int id = 0;
        Team team = new Team();

        Mockito.when(namedParameterJdbcTemplate.queryForObject(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<RowMapper<Team>>any())
        ).thenReturn(team);

        Team result = teamDaoJDBC.getTeamById(id);

        Mockito.verify(namedParameterJdbcTemplate)
                .queryForObject(eq(sql), captorSource.capture(), captorMapper.capture());

        SqlParameterSource source = captorSource.getValue();
        RowMapper<Team> mapper = captorMapper.getValue();

        Assertions.assertNotNull(source);
        Assertions.assertNotNull(mapper);

        Assertions.assertNotNull(result);
        Assertions.assertSame(team, result);
    }
}