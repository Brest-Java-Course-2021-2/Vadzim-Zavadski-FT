package com.zavadski.dao;

import com.zavadski.model.dto.TeamDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  Team DTO DAO implementation.
 */
@Component
public class TeamDtoDaoJdbc implements TeamDtoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String findAllWithNumberOfPlayersSql = "SELECT\n" +
            "\tt.team_id AS teamId,\n" +
            "\tt.team_name AS teamName,\n" +
            "\tcount(p.team_id) AS numberOfPlayers\n" +
            "FROM\n" +
            "\tteam t\n" +
            "LEFT JOIN player p ON\n" +
            "\tt.team_id = p.team_id\n" +
            "GROUP BY\n" +
            "\tt.team_id,\n" +
            "\tt.team_name\n" +
            "ORDER BY\n" +
            "\tteam_name";

    public TeamDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<TeamDto> findAllWithNumberOfPlayers() {
        List<TeamDto> teams = namedParameterJdbcTemplate.query(findAllWithNumberOfPlayersSql,
                BeanPropertyRowMapper.newInstance(TeamDto.class));
        return teams;
    }
}