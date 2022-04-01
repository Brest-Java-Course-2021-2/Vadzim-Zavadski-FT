package com.zavadski.service.config;

import com.zavadski.dao.api.PlayerDao;
import com.zavadski.dao.api.PlayerDtoDao;
import com.zavadski.dao.api.TeamDao;
import com.zavadski.dao.api.TeamDtoDao;
import com.zavadski.dao.jdbc.PlayerDaoJDBCImpl;
import com.zavadski.dao.jdbc.PlayerDtoDaoJdbc;
import com.zavadski.dao.jdbc.TeamDaoJDBCImpl;
import com.zavadski.dao.jdbc.TeamDtoDaoJdbc;
import com.zavadski.service.PlayerDtoService;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamDtoService;
import com.zavadski.service.TeamService;
import com.zavadski.service.impl.PlayerDtoServiceImpl;
import com.zavadski.service.impl.PlayerServiceImpl;
import com.zavadski.service.impl.TeamDtoServiceImpl;
import com.zavadski.service.impl.TeamServiceImpl;
import com.zavadski.testdb.SpringJdbcConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServiceTestConfig extends SpringJdbcConfig {

    @Bean
    TeamDtoDao teamDtoDao() {
        return new TeamDtoDaoJdbc(namedParameterJdbcTemplate());
    }

    @Bean
    TeamDtoService teamDtoService() {
        return new TeamDtoServiceImpl(teamDtoDao());
    }

    @Bean
    TeamDao teamDao() {
        return new TeamDaoJDBCImpl(namedParameterJdbcTemplate());
    }

    @Bean
    TeamService teamService() {
        return new TeamServiceImpl(teamDao());
    }

    @Bean
    PlayerDtoDao playerDtoDao() {
        return new PlayerDtoDaoJdbc(namedParameterJdbcTemplate());
    }

    @Bean
    PlayerDtoService playerDtoService() {
        return new PlayerDtoServiceImpl(playerDtoDao());
    }

    @Bean
    PlayerDao playerDao() {
        return new PlayerDaoJDBCImpl(namedParameterJdbcTemplate());
    }

    @Bean
    PlayerService playerService() {
        return new PlayerServiceImpl(playerDao());
    }
}
