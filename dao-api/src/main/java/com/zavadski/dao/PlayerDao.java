package com.zavadski.dao;

import com.zavadski.model.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlayerDao {

    /**
     * Get all players from the database.
     *
     * @return players list.
     */
    List<Player> findAllPlayers();

    Optional<Player> findPlayerById(Integer playerId);

    /**
     * Get player by Id.
     *
     * @param playerId player Id.
     * @return player.
     */

    Player getPlayerById(Integer playerId);

    Integer create(Player player);

    Integer update(Player player);

    Integer delete(Integer playerId);

}
