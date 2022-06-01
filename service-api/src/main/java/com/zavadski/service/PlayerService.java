package com.zavadski.service;

import com.zavadski.model.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player getPlayerById(Integer playerId);

    Integer create(Player player);

    Integer update(Player player);

    void delete(Integer playerId);

}