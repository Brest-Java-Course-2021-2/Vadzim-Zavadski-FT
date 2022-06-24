package com.zavadski.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySources({@PropertySource("config.properties"), @PropertySource("sql_query.properties")})
public class SpringJdbcConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Profile("test")
    public DataSource dataSourceH2() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create_and_init_db.sql")
                .build();
    }

    @Profile("dev")
    public DataSource dataSourcePostgres() {
        return new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/football_teams"
                , System.getProperty("db_user")
                , System.getProperty("db_pass"));
    }

    public DataSource dataSource() {
        if (Objects.equals(activeProfile, "dev")) {
            return dataSourcePostgres();
        }
        return dataSourceH2();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
