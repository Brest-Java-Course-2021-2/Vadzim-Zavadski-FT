package com.zavadski.testdb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class SpringJdbcConfig {

    @Bean
    public DataSource dataSource() {

//        return new DriverManagerDataSource(
//                "jdbc:mysql://localhost:3306/Vadzim-Zavadski-FT","epam","epam");

        return new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/Vadzim-Zavadski-FT","epam","epam");

//        return new DriverManagerDataSource(
//                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1","sa","");

//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("create_and_init_db.sql")
//                .build();

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
