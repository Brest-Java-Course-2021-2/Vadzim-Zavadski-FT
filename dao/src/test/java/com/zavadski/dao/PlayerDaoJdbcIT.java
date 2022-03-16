package com.zavadski.dao;

import com.zavadski.testdb.SpringJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import({PlayerDaoJDBCImpl.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback
class PlayerDaoJdbcIT {

    private PlayerDaoJDBCImpl playerDaoJDBC;

    public PlayerDaoJdbcIT(@Autowired PlayerDao playerDaoJdbc) {
        this.playerDaoJDBC = (PlayerDaoJDBCImpl) playerDaoJdbc;
    }

    @Test
    void getAllPlayers() {

        assertNotNull(playerDaoJDBC);
        assertNotNull(playerDaoJDBC.getAllPlayers());
    }
}
