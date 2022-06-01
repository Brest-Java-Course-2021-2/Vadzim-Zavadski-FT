package com.zavadski.dao.api;

import com.zavadski.model.Player;

import java.util.List;

public interface PlayerDao {

    List<Player> findAll();

    Player findById(Integer playerId);

    Integer save(Player player);

    Integer update(Player player);

    void delete(Integer playerId);

}
