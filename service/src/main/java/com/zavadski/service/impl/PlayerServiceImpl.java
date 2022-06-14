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
    public Player findPlayerById(Integer id) {
        return playerDao.findById(id);
    }

    @Override
    public Integer createPlayer(Player player) {
        return playerDao.create(player);
    }

    @Override
    public Integer updatePlayer(Player player) {
        return playerDao.update(player);
    }

    @Override
    public Integer deletePlayer(Integer playerId) {
        return playerDao.delete(playerId);
    }
}
