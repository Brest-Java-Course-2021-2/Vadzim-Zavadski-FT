package com.zavadski.dao;

import com.zavadski.model.Team;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TeamDaoJDBCImpl implements TeamDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TeamDaoJDBCImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Integer create(Team team) {
        return null;
    }

    @Override
    public Integer update(Team team) {
        return null;
    }

    @Override
    public Integer delete(Team team) {
        return null;
    }
}
