package com.zavadski.dao.jdbc;

import com.zavadski.dao.BiographyDao;
import com.zavadski.model.Biography;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class BiographyDaoJDBCImpl implements BiographyDao {

    private final Logger logger = LogManager.getLogger(BiographyDaoJDBCImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_BIOGRAPHY_FOR_PLAYER}")
    private String sqlGetBiographyByPlayerId;

    @Autowired
    public BiographyDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Biography getBiographyByPlayerId(Integer playerId) {

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("playerId", playerId);

        return namedParameterJdbcTemplate.queryForObject(sqlGetBiographyByPlayerId,
                sqlParameterSource, BeanPropertyRowMapper.newInstance(Biography.class));
    }

}
