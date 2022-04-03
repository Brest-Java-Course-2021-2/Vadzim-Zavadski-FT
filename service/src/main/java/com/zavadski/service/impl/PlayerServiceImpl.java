package com.zavadski.service.impl;

import com.zavadski.dao.api.PlayerDao;
import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> getAllPlayers() {
        return playerDao.getAllPlayers();
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        return playerDao.getPlayerById(playerId);
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
