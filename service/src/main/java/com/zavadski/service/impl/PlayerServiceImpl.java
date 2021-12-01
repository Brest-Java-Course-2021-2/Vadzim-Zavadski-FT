package com.zavadski.service.impl;

import com.zavadski.dao.PlayerDao;
import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    private final PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findAll() {
        logger.debug("Service method called to find all Player");
        return playerDao.findAll();
    }

    @Override
    public Optional<Player> findById(Integer playerId) {
        logger.debug("findById(id:{})", playerId);
        return playerDao.findById(playerId);
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        logger.debug("Get player by id = {}", playerId);
        return this.playerDao.getPlayerById(playerId);
    }

    @Override
    public Integer create(Player player) {
        logger.debug("create({})", player);
        return this.playerDao.create(player);
    }

    @Override
    public Integer update(Player player) {
        logger.debug("update({})", player);
        return this.playerDao.update(player);
    }

    @Override
    public Integer delete(Integer playerId) {
        logger.debug("delete player with id = {}", playerId);
        return this.playerDao.delete(playerId);
    }

}