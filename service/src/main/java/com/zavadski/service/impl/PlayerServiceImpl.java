package com.zavadski.service.impl;

import com.zavadski.dao.PlayerDao;
import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    private final PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> getAllPlayers() {
        logger.debug("Service method called to find all Player");
        return playerDao.findAllPlayers();
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        logger.debug("findById(id:{})", playerId);
        return playerDao.getPlayerById(playerId);
    }

    @Override
    public Integer create(Player player) {
        logger.debug("create({})", player);
        return playerDao.create(player);
    }

    @Override
    public Integer update(Player player) {
        logger.debug("update({})", player);
        return playerDao.update(player);
    }

    @Override
    public Integer delete(Integer playerId) {
        logger.debug("delete player with id = {}", playerId);
        return playerDao.delete(playerId);
    }
}
