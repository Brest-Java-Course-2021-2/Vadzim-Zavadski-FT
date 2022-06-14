package com.zavadski.dao.jdbc;

import com.zavadski.dao.api.PlayerDao;
import com.zavadski.model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
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

    @Autowired
    public PlayerDaoJDBCImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Player> findAll() {

        logger.debug("find All Players");

        return namedParameterJdbcTemplate.query(sqlGetAllPlayers, new PlayerRowMapper());
    }

    @Override
    public Player findById(Integer id) {

        logger.debug("Find Player by id={}", id);

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("playerId", id);
        return namedParameterJdbcTemplate.queryForObject(sqlGetPlayerById, sqlParameterSource, new PlayerRowMapper());
    }

    @Override
    public Integer create(Player player) {

        logger.debug("Create player {})", player);

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("firstName", player.getFirstName()).
                        addValue("surname", player.getSurname()).
                        addValue("birthday", player.getBirthday()).
                        addValue("teamId", player.getTeamId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(sqlCreatePlayer, sqlParameterSource, keyHolder);
    }

    @Override
    public Integer update(Player player) {

        logger.debug("Update player: update({})", player);

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

    public static class PlayerRowMapper implements RowMapper<Player> {

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
