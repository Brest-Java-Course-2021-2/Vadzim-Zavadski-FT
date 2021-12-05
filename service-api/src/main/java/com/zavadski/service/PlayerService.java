package com.zavadski.service;

import com.zavadski.model.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlayerService {

    List<Player> findAllPlayers();

    Optional<Player> getPlayerById(Integer playerId);

    Integer create(Player player);

    Integer update(Player player);

    Integer delete(Integer playerId);

}