package com.zavadski.dao;

import com.zavadski.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamDaoJDBCImpl implements TeamDao{

    private final Logger LOGGER = LogManager.getLogger(TeamDaoJDBCImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_ALL_TEAMS="select t.team_id, t.team_name from team t order by t.team_name";
    private final String SQL_TEAM_BY_ID = "select t.team_id, t.team_name from team t " +
            " where team_id = :teamId";
    private final String SQL_CHECK_UNIQUE_TEAM_NAME="select count(t.team_name) \" +\n" +
            "            \"from team t where lower(t.team_name) = lower(:teamName)";
    private final String SQL_CREATE_TEAM="insert into team(team_name) values(:teamName)";
    private final String SQL_UPDATE_TEAM_NAME="update team set team_name = :teamName " +
            "where team_id = :teamId";
    private final String SQL_DELETE_TEAM_BY_ID = "delete from team where team_id = :teamId";

    @Deprecated
    public TeamDaoJDBCImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public TeamDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Team> findAll() {
        LOGGER.debug("Start: findAll()");
        return namedParameterJdbcTemplate.query(SQL_ALL_TEAMS, new TeamRowMapper());
    }

    @Override
    public Team getTeamById(Integer teamId) {
        LOGGER.debug("Get team by id = {}", teamId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamId", teamId);
        return namedParameterJdbcTemplate.queryForObject(SQL_TEAM_BY_ID, sqlParameterSource, new TeamRowMapper());
    }

    @Override
    public Integer create(Team team) {
        LOGGER.debug("Create team: create({})", team);

        //TODO: isTeamUnique throw new IllegalArgumentException
        if (!isTeamUnique(team.getTeamName())) {
            LOGGER.warn("Team with the same name {} already exists.", team.getTeamName());
            throw new IllegalArgumentException("Team with the same name already exists in DB.");
        }

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamName", team.getTeamName().toUpperCase());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_TEAM, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    private boolean isTeamUnique(String teamName){
        LOGGER.debug("Check TeamName: {} on unique", teamName);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("teamName", teamName);
        return namedParameterJdbcTemplate.queryForObject(SQL_CHECK_UNIQUE_TEAM_NAME, sqlParameterSource, Integer.class) == 0;
    }

    @Override
    public Integer update(Team team) {
        LOGGER.debug("Update team: create({})", team);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamName", team.getTeamName()).
                        addValue("teamId", team.getTeamId());
        return namedParameterJdbcTemplate.update(SQL_UPDATE_TEAM_NAME, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer teamId) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamId", teamId);
        return namedParameterJdbcTemplate.update(SQL_DELETE_TEAM_BY_ID, sqlParameterSource);
    }

    @Override
    public Integer count() {
        LOGGER.debug("count()");
        return namedParameterJdbcTemplate
                .queryForObject("select count(*) from team", new MapSqlParameterSource(), Integer.class);
        //TODO: SELECT_COUNT_FROM_TEAM
        //public static final String SELECT_COUNT_FROM_TEAM = "select count(*) from team";
    }

    private class TeamRowMapper implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet resultSet, int i) throws SQLException {
            Team team = new Team();
            team.setTeamId(resultSet.getInt("team_id"));
            team.setTeamName(resultSet.getString("team_name"));
            return team;
        }
    }

}
