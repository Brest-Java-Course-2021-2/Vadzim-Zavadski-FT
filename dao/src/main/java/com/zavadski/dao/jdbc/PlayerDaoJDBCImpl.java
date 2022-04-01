package com.zavadski.dao.jdbc;

import com.zavadski.dao.api.PlayerDao;
import com.zavadski.dao.jdbc.exception.FieldNullPointerException;
import com.zavadski.dao.jdbc.exception.UnacceptableName;
import com.zavadski.model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.zavadski.model.constants.PlayerConstants.PLAYER_NAME_SIZE;

@Component
public class PlayerDaoJDBCImpl implements PlayerDao {

    private final Logger logger = LogManager.getLogger(PlayerDaoJDBCImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public List<Player> getAllPlayers() {

        logger.debug("Start: findAll()");

        return namedParameterJdbcTemplate.query(sqlGetAllPlayers, new PlayerRowMapper());
    }

    @Override
    public Player getPlayerById(Integer playerId) {

        logger.debug("Get player by id = {}", playerId);

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("playerId", playerId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetPlayerById, sqlParameterSource, new PlayerRowMapper());
    }

    @Override
    public Integer create(Player player) {

        logger.debug("Create player: create({})", player);

        if (player.getFirstName().length() > PLAYER_NAME_SIZE) {
            logger.warn("Player name is too long {}", player.getFirstName());
            throw new UnacceptableName("Player name length should be <=" + PLAYER_NAME_SIZE);
        }

        if (!isPlayerUnique(player.getFirstName(), player.getSurname(), player.getBirthday(), 0)) {
            logger.warn("Player {} {} {} already exists.", player.getFirstName(), player.getSurname(), player.getBirthday());
            throw new UnacceptableName("Player with the same name and surname already exists in DB.");
        }

        if (player.getFirstName().isEmpty() || player.getSurname().isEmpty() || player.getBirthday() == null) {
            logger.error("Not all fields are filled in Player");
            throw new FieldNullPointerException("Not all fields are filled in Player");
        }

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("firstName", player.getFirstName()).
                        addValue("surname", player.getSurname()).
                        addValue("birthday", player.getBirthday()).
                        addValue("teamId", player.getTeamId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(sqlCreatePlayer, sqlParameterSource, keyHolder);
    }

    private boolean isPlayerUnique(String firstName, String surname, LocalDate birthday, Integer count) {

        logger.debug("Check Player: {} {} {} on unique", firstName, surname, birthday);

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("firstName", firstName).
                        addValue("surname", surname).
                        addValue("birthday", birthday);
        return namedParameterJdbcTemplate.queryForObject(sqlCheckUniqueFirstName, sqlParameterSource, Integer.class) <= count;
    }

    @Override
    public Integer update(Player player) {

        logger.debug("Update player: update({})", player);

        if (player.getFirstName().length() > PLAYER_NAME_SIZE) {
            logger.warn("Player name {} is too long", player.getFirstName());
            throw new UnacceptableName("Player name length should be <=" + PLAYER_NAME_SIZE);
        }

        if (!isPlayerUnique(player.getFirstName(), player.getSurname(), player.getBirthday(), 1)) {
            logger.warn("Player {} {} {} already exists.", player.getFirstName(), player.getSurname(), player.getBirthday());
            throw new UnacceptableName("Player with the same name and surname already exists in DB.");
        }

        if (player.getFirstName().isEmpty() || player.getSurname().isEmpty() || player.getBirthday() == null) {
            logger.error("Not all fields are filled in Player");
            throw new FieldNullPointerException("Not all fields are filled in Player");
        }

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("playerId", player.getPlayerId()).
                        addValue("firstName", player.getFirstName()).
                        addValue("surname", player.getSurname()).
                        addValue("birthday", player.getBirthday()).
                        addValue("teamId", player.getTeamId());
        return namedParameterJdbcTemplate.update(sqlUpdatePlayer, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer playerId) {

        logger.debug("Delete player by id = {})", playerId);

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
            player.setBirthday(resultSet.getDate("birthday").toLocalDate());
            player.setTeamId(resultSet.getInt("team_id"));
            return player;
        }

    }
}
