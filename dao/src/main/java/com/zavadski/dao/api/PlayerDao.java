package com.zavadski.dao.api;

import com.zavadski.model.Player;

import java.util.List;

public interface PlayerDao {

    List<Player> findAll();

    Player findById(Integer playerId);

    Integer create(Player player);

    Integer update(Player player);

    Integer delete(Integer playerId);

}
