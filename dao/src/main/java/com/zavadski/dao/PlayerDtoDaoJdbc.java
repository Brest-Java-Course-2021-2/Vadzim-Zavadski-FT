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
    @Value("${SQL_FILTER_BY_BIRTHDAY}")
    private String sqlFilterByBirthday;

    public PlayerDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate) {
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
            return sqlFilterByBirthday;
        }
        if (startDate == null) {
            logger.debug("Filtering only by end date");
            return sqlFilterByBirthday;
        }
        if (endDate == null) {
            logger.debug("Filtering only by start date");
            return sqlFilterByBirthday;
        }
        return sqlFilterByBirthday;
    }
}