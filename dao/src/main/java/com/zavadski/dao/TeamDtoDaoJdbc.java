package com.zavadski.dao;

import com.zavadski.model.dto.TeamDto;
import org.springframework.beans.factory.annotation.Value;
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
@Value("${findAllWithNumberOfPlayersSql}")
private String findAllWithNumberOfPlayersSql;

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