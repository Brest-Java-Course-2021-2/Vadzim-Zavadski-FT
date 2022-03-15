package com.zavadski.testdb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:application.properties"})
public class SpringJdbcConfig {

    private String datadase;

    @Profile("h2")
    public DataSource dataSourceH2() {
        datadase = "h2";
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create_and_init_db.sql")
                .build();
    }

    @Profile("postgresql")
    public DataSource dataSourcePostgresql() {
        datadase = "postgresql";
        return new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/Vadzim-Zavadski-FT"
                , "epam"
                , "epam");
    }

    @Bean
    public DataSource dataSource() {
        if (Objects.equals(datadase, "h2")) {
            return dataSourceH2();
        } else return dataSourcePostgresql();
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
