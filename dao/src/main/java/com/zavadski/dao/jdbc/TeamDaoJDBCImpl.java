package com.zavadski.dao.jdbc;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.dao.jdbc.exception.TeamWithPlayerException;
import com.zavadski.dao.jdbc.exception.UnacceptableName;
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
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeamDaoJDBCImpl implements TeamDao {

    private final Logger logger = LogManager.getLogger(TeamDaoJDBCImpl.class);

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

    @Value("${SQL_PLAYER_COUNT_FOR_TEAM}")
    private String sqlPlayersCountForTeam;

    public TeamDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Team> findAll() {

        logger.debug("Start: findAll()");

        return namedParameterJdbcTemplate.query(sqlGetAllTeams, new TeamRowMapper());
    }

    @Override
    public Team getTeamById(Integer teamId) {

        logger.debug("Get team by id = {}", teamId);

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamId", teamId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetTeamById, sqlParameterSource, new TeamRowMapper());
    }

    @Override
    public Integer create(Team team) {

        logger.debug("Create team: create({})", team);

        if (!checkTeamOnUnique(team.getTeamName())) {
            logger.warn("Team with the same name {} already exists.", team.getTeamName());
            throw new UnacceptableName("Team with the same name already exists in DB.");
        }

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamName", team.getTeamName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(sqlCreateTeam, sqlParameterSource, keyHolder);
    }

    @Override
    public boolean checkTeamOnUnique(String teamName) {

        logger.debug("Check TeamName: {} on unique", teamName);

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("teamName", teamName);
        Long result = namedParameterJdbcTemplate.queryForObject(sqlCheckUniqueTeamName, sqlParameterSource, Long.class);
        return result == 0;
    }

    @Override
    public Integer update(Team team) {

        logger.debug("Update team: update({})", team);

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamName", team.getTeamName()).
                        addValue("teamId", team.getTeamId());
        return namedParameterJdbcTemplate.update(sqlUpdateTeamName, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer teamId) {

        logger.debug("Delete team by id = {}", teamId);

        if (isTeamWithPlayers(teamId)) {
            logger.error("Can't delete team id: " + teamId + ". This team have a players");
            throw new TeamWithPlayerException("Can't delete team id: " + teamId + ". This team have a players");
        }
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamId", teamId);
        return namedParameterJdbcTemplate.update(sqlDeleteTeamById, sqlParameterSource);
    }

    @Override
    public boolean isTeamWithPlayers(Integer teamId) {
        return getPlayersCountForTeam(teamId) > 0;
    }

    private Integer getPlayersCountForTeam(Integer teamId) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamId", teamId);
        return namedParameterJdbcTemplate.queryForObject(
                sqlPlayersCountForTeam, sqlParameterSource, Integer.class);
    }

    @Override
    public Integer count() {

        logger.debug("count()");

        return (namedParameterJdbcTemplate
                .queryForObject(sqlTeamCount, new MapSqlParameterSource(), Integer.class));
    }

    private static class TeamRowMapper implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet resultSet, int i) throws SQLException {
            Team team = new Team();
            team.setTeamId(resultSet.getInt("team_id"));
            team.setTeamName(resultSet.getString("team_name"));
            return team;
        }
    }
}
