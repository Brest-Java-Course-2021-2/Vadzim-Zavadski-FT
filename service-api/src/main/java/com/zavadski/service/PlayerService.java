package com.zavadski.service;

import com.zavadski.model.Player;

import java.util.List;

public interface PlayerService {

    List<Player> findAllPlayers();

    Player getPlayerById(Integer playerId);

    Integer create(Player player);

    Integer update(Player player);

    Integer delete(Integer playerId);

}
