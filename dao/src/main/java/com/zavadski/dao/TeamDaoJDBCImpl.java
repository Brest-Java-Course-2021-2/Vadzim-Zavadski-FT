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

    private final Logger logger = LogManager.getLogger(TeamDaoJDBCImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_ALL_TEAMS="select t.teamId, t.teamName from team t order by t.teamName";
    private final String SQL_CREATE_TEAM="insert into team(teamName) values(:teamName)";

    public TeamDaoJDBCImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Team> findAll() {
        logger.debug("Start: findAll()");
        return namedParameterJdbcTemplate.query(SQL_ALL_TEAMS, new TeamRowMapper());
    }

    @Override
    public Integer create(Team team) {
        logger.debug("Start: create({})", team);

        //TODO: isDepartmentUnique throw new IllegalArgumentException

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("teamName", team.getTeamName().toUpperCase());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_TEAM, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(Team team) {
        return null;
    }

    @Override
    public Integer delete(Team team) {
        return null;
    }

    private class TeamRowMapper implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet resultSet, int i) throws SQLException {
            Team team = new Team();
            team.setTeamId(resultSet.getInt("teamId"));
            team.setTeamName(resultSet.getString("teamName"));
            return team;
        }
    }

}
