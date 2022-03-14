//package com.zavadski.dao;
//
//import com.zavadski.model.Team;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//
//@ExtendWith(MockitoExtension.class)
//public class TeamDaoJDBCImplTest {
//
//    @InjectMocks
//    private TeamDaoJDBCImpl teamDaoJDBC;
//
//    @Mock
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Captor
//    private ArgumentCaptor<RowMapper<Team>> captorMapper;
//
//    @Captor
//    private ArgumentCaptor<SqlParameterSource> captorSource;
//
//    @Captor
//    private ArgumentCaptor<GeneratedKeyHolder> captorKeyHolder;
//
//    @AfterEach
//    public void check() {
//        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
//    }
//
//    @Test
//    public void findAll() {
//
//        String sql = "select";
//        ReflectionTestUtils.setField(teamDaoJDBC, "sqlGetAllTeams", sql);
//        Team team = new Team();
//        List<Team> list = Collections.singletonList(team);
//
//        Mockito.when(namedParameterJdbcTemplate.query(
//                any(),
//                ArgumentMatchers.<RowMapper<Team>>any())
//        ).thenReturn(list);
//
//        List<Team> result = teamDaoJDBC.findAll();
//
//        Assertions.assertNotNull(result);
//        Assertions.assertFalse(result.isEmpty());
//        Assertions.assertSame(team, result.get(0));
//
//        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql), captorMapper.capture());
//        RowMapper<Team> mapper = captorMapper.getValue();
//
//        Assertions.assertNotNull(mapper);
//    }
//
//    @Test
//    public void getTeamById() {
//
//        String sql = "get";
//        ReflectionTestUtils.setField(teamDaoJDBC, "sqlGetTeamById", sql);
//        int id = 0;
//        Team team = new Team();
//
//        Mockito.when(namedParameterJdbcTemplate.queryForObject(
//                any(),
//                ArgumentMatchers.<SqlParameterSource>any(),
//                ArgumentMatchers.<RowMapper<Team>>any())
//        ).thenReturn(team);
//
//        Team result = teamDaoJDBC.getTeamById(id);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertSame(team, result);
//
//        Mockito.verify(namedParameterJdbcTemplate)
//                .queryForObject(eq(sql), captorSource.capture(), captorMapper.capture());
//
//        SqlParameterSource source = captorSource.getValue();
//        RowMapper<Team> mapper = captorMapper.getValue();
//
//        Assertions.assertNotNull(source);
//        Assertions.assertNotNull(mapper);
//    }
//
//    @Test
//    public void count() {
//
//        String sql = "select";
//        ReflectionTestUtils.setField(teamDaoJDBC, "sqlTeamCount", sql);
//
//        Team team1 = new Team("team1");
//        Team team2 = new Team("team2");
//        List<Team> list = List.of(team1, team2);
//        Integer count = list.size();
//
//        Mockito.when(namedParameterJdbcTemplate.queryForObject(
//                any(),
//                ArgumentMatchers.<MapSqlParameterSource>any(),
//                eq(Integer.class))
//        ).thenReturn(count);
//
//        Integer result = teamDaoJDBC.count();
//
//        Assertions.assertNotNull(result);
//        Assertions.assertSame(count, result);
//
//        Mockito.verify(namedParameterJdbcTemplate)
//                .queryForObject(eq(sql), captorSource.capture(), eq(Integer.class));
//
//        SqlParameterSource source = captorSource.getValue();
//
//        Assertions.assertNotNull(source);
//    }
//
//
//    //todo  не получается: isTeamWithPlayers
////    @Test
////    public void deleteTeamById() {
////
////        String sql = "delete";
////        ReflectionTestUtils.setField(teamDaoJDBC, "sqlDeleteTeamById", sql);
////
////        int id = 0;
////        Team team = new Team();
////        List<Team> list = Collections.singletonList(team);
////
////        Mockito.when(namedParameterJdbcTemplate.update(
////                any(),
////                ArgumentMatchers.<SqlParameterSource>any()
////        )).thenReturn(id);
////
////        Integer result = teamDaoJDBC.delete(id);
////
////        Assertions.assertNotNull(result);
////
////    }
//
////    @Test
////    public void isTeamUnique() {
////        String sql = "create";
////        ReflectionTestUtils.setField(teamDaoJDBC, "sqlCheckUniqueTeamName", sql);
////        String teamName = "Li";
////        Team team = new Team();
////
////        Mockito.when(namedParameterJdbcTemplate.queryForObject(
////                any(),
////                ArgumentMatchers.<SqlParameterSource>any(),
////                ArgumentMatchers.<Class<Integer>>any())).thenReturn(0);
////
////    }
//
////    @Test
////    public void createTeam() {
////
//////        String sql = "create";
//////        ReflectionTestUtils.setField(teamDaoJDBC, "sqlCreateTeam", sql);
////        Team team = new Team("T");
////        Integer count = 0;
//////        isTeamUnique(team.getTeamName())
////        Mockito.when(namedParameterJdbcTemplate.update(
////                any(),
////                ArgumentMatchers.<SqlParameterSource>any(),
////                ArgumentMatchers.<KeyHolder>any())
////        ).thenReturn(count);
////
////        Integer result = teamDaoJDBC.create(team);
////
////        Assertions.assertNotNull(result);
//
////        Mockito.verify(namedParameterJdbcTemplate)
////                .update(eq(sql), captorSource.capture(), captorKeyHolder.capture());
//
////        SqlParameterSource source = captorSource.getValue();
////        KeyHolder keyHolder = captorKeyHolder.getValue();
////
////        Assertions.assertNotNull(source);
////        Assertions.assertNotNull(keyHolder);
////    }
//
//}