package com.zavadski.service;

import com.zavadski.model.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player findPlayerById(Integer playerId);

    Integer createPlayer(Player player);

    Integer updatePlayer(Player player);

    Integer deletePlayer(Integer playerId);

}
