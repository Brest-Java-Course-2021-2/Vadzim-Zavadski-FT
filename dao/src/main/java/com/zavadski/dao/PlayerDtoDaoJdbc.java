package com.zavadski.dao;

import com.zavadski.model.dto.PlayerDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class PlayerDtoDaoJdbc implements PlayerDtoDao {

    private final Logger logger = LogManager.getLogger(PlayerDtoDaoJdbc.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_ALL_PLAYERS}")
    private String sqlGetAllPlayers;

    @Value("${SQL_FILTER_BY_START_END_DATE}")
    private String sqlFilterByStartEndDate;

    @Value("${SQL_FILTER_BY_END_DATE}")
    private String sqlFilterByEndDate;

    @Value("${SQL_FILTER_BY_START_DATE}")
    private String sqlFilterByStartDate;

    public PlayerDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate) {
        logger.debug("filterByBirthday()");
        logger.debug("Start date: " + startDate);
        logger.debug("End date: " + endDate);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("START_DATE", startDate);
        parameterSource.addValue("END_DATE", endDate);
        return namedParameterJdbcTemplate.query(
                sqlGetFilterByBirthday(startDate, endDate),
                parameterSource,
                BeanPropertyRowMapper.newInstance(PlayerDto.class));
    }

    private String sqlGetFilterByBirthday(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) {
            logger.debug("Don't use a filter");
            return sqlGetAllPlayers;
        }
        if (startDate == null) {
            logger.debug("Filter by end date");
            return sqlFilterByEndDate;
        }
        if (endDate == null) {
            logger.debug("Filter by start date");
            return sqlFilterByStartDate;
        }
        return sqlFilterByStartEndDate;
    }
}
