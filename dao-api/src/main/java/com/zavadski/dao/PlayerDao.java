package com.zavadski.dao;

import com.zavadski.model.Player;

import java.util.List;

public interface PlayerDao {

    List<Player> findAllPlayers();

    Player getPlayerById(Integer playerId);

    Integer create(Player player);

    Integer update(Player player);

    Integer delete(Integer playerId);

}
