package com.zavadski.service.impl;

import com.zavadski.dao.api.PlayerDao;
import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;

    @Autowired
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.findAll();
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        return playerDao.findById(playerId);
    }

    @Override
    public Integer create(Player player) {
        return playerDao.create(player);
    }

    @Override
    public Integer update(Player player) {
        return playerDao.update(player);
    }

    @Override
    public Integer delete(Integer playerId) {
        return playerDao.delete(playerId);
    }
}
