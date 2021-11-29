package com.zavadski.dao;

import com.zavadski.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TeamDaoJDBCImpl implements TeamDao {

    private final Logger LOGGER = LogManager.getLogger(TeamDaoJDBCImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_TEAMS_COUNT}")
    public String sqlTeamCount;

    @Value("${SQL_ALL_TEAMS}")
    private String sqlGetAllTeams;

    @Value("${SQL_TEAM_BY_ID}")
    private String sqlGetTeamById;

    @Value("${SQL_CHECK_UNIQUE_TEAM_NAME}")
    private String sqlCheckUniqueTeamName;

    @Value("${SQL_CREATE_TEAM}")
    private String sqlCreateTeam;

    @Value("${SQL_UPDATE_TEAM_NAME}")
    private String sqlUpdateTeamName;

    @Value("${SQL_DELETE_TEAM_BY_ID}")
    private String sqlDeleteTeamById;

    public TeamDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Team> findAll() {
        LOGGER.debug("Start: findAll()");
        return namedParameterJdbcTemplate.query(sqlGetAllTeams, new TeamRowMapper());
    }

    @Override
    public Team getTeamById(Integer teamId) {
        LOGGER.debug("Get team by id = {}", teamId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamId", teamId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetTeamById, sqlParameterSource, new TeamRowMapper());
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
        namedParameterJdbcTemplate.update(sqlCreateTeam, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    private boolean isTeamUnique(String teamName) {
        LOGGER.debug("Check TeamName: {} on unique", teamName);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("teamName", teamName);
        return namedParameterJdbcTemplate.queryForObject(sqlCheckUniqueTeamName, sqlParameterSource, Integer.class) == 0;
    }

    @Override
    public Integer update(Team team) {
        LOGGER.debug("Update team: create({})", team);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamName", team.getTeamName()).
                        addValue("teamId", team.getTeamId());
        return namedParameterJdbcTemplate.update(sqlUpdateTeamName, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer teamId) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamId", teamId);
        return namedParameterJdbcTemplate.update(sqlDeleteTeamById, sqlParameterSource);
    }

    @Override
    public Integer count() {
        LOGGER.debug("count()");
        return namedParameterJdbcTemplate
                .queryForObject(sqlTeamCount, new MapSqlParameterSource(), Integer.class);
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