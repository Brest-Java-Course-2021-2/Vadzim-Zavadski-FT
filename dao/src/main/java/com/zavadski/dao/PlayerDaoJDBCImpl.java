package com.zavadski.dao;

import com.zavadski.model.Player;
import com.zavadski.model.dto.TeamDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PlayerDaoJDBCImpl implements PlayerDao {

    private final Logger LOGGER = LogManager.getLogger(PlayerDaoJDBCImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${SQL_ALL_PLAYERS}")
    private String sqlGetAllPlayers;

    @Value("${SQL_PLAYER_BY_ID}")
    private String sqlGetPlayerById;

    @Value("${SQL_CHECK_UNIQUE_PLAYER_NAME}")
    private String sqlCheckUniqueFirstName;

    @Value("${SQL_CREATE_PLAYER}")
    private String sqlCreatePlayer;

    @Value("${SQL_UPDATE_PLAYER}")
    private String sqlUpdatePlayer;

    @Value("${SQL_DELETE_PLAYER_BY_ID}")
    private String sqlDeletePlayerById;

    public PlayerDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Player> findAll() {
        LOGGER.debug("Start: findAll()");
        return namedParameterJdbcTemplate.query(sqlGetAllPlayers, new PlayerRowMapper());
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        LOGGER.debug("Get player by id = {}", playerId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("playerId", playerId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetPlayerById, sqlParameterSource, new PlayerRowMapper());
    }

    @Override
    public Integer create(Player player) {
        LOGGER.debug("Create player: create({})", player);

        //TODO: isPlayerUnique throw new IllegalArgumentException
        if (!isPlayerUnique(player.getFirstName())) {
            LOGGER.warn("Player with the same name {} already exists.", player.getFirstName());
            throw new IllegalArgumentException("Player with the same name already exists in DB.");
        }

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("firstName", player.getFirstName()).
                        addValue("surname", player.getSurname()).
                        addValue("birthday", player.getBirthday());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlCreatePlayer, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    private boolean isPlayerUnique(String firstName) {
        LOGGER.debug("Check FirstName: {} on unique", firstName);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("firstName", firstName);
        return namedParameterJdbcTemplate.queryForObject(sqlCheckUniqueFirstName, sqlParameterSource, Integer.class) == 0;
    }

    @Override
    public Integer update(Player player) {
        LOGGER.debug("Update player: update({})", player);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("firstName", player.getPlayerId()).
                        addValue("playerId", player.getFirstName()).
                        addValue("birthday", player.getSurname()).
                        addValue("surname", player.getBirthday());
        return namedParameterJdbcTemplate.update(sqlUpdatePlayer, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer playerId) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("playerId", playerId);
        return namedParameterJdbcTemplate.update(sqlDeletePlayerById, sqlParameterSource);
    }

    private class PlayerRowMapper implements RowMapper<Player> {

        @Override
        public Player mapRow(ResultSet resultSet, int i) throws SQLException {
            Player player = new Player();
            player.setPlayerId(resultSet.getInt("player_id"));
            player.setFirstName(resultSet.getString("first_name"));
            player.setSurname(resultSet.getString("surname"));
            player.setBirthday(resultSet.getDate("birthday"));
            return player;
        }
    }
}