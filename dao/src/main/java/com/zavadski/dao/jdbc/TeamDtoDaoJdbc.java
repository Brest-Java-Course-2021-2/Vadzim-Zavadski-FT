package com.zavadski.dao.jdbc;

import com.zavadski.dao.api.TeamDtoDao;
import com.zavadski.model.dto.TeamDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDtoDaoJdbc implements TeamDtoDao {

    private final Logger logger = LogManager.getLogger(TeamDtoDaoJdbc.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${findAllWithNumberOfPlayersSql}")
    private String findAllWithNumberOfPlayersSql;

    public TeamDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<TeamDto> findAllWithNumberOfPlayers() {

        logger.debug("Start: findAllWithNumberOfPlayers");

        List<TeamDto> teams = namedParameterJdbcTemplate.query(findAllWithNumberOfPlayersSql,
                BeanPropertyRowMapper.newInstance(TeamDto.class));
        return teams;
    }
}
