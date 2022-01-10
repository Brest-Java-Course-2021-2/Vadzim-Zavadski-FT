package com.zavadski.service.impl;

import com.zavadski.dao.TeamDao;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    private final TeamDao teamDao;

    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Team> findAllTeams() {
        logger.debug("Service method called to find all Team");
        return teamDao.findAll();
    }

    @Override
    public Team getTeamById(Integer teamId) {
        logger.debug("Service method Get team by id = {}", teamId);
        return this.teamDao.getTeamById(teamId);
    }

    @Override
    @Transactional
    public Integer create(Team team) {
        logger.debug("Service method create({})", team);
        return this.teamDao.create(team);
    }

    @Override
    public Integer update(Team team) {
        logger.debug("Service method update({})", team);
        return this.teamDao.update(team);
    }

    @Override
    public Integer delete(Integer teamId) {
        logger.debug("Service method delete team with id = {}", teamId);
        return this.teamDao.delete(teamId);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        logger.debug("Service method count()");
        return this.teamDao.count();
    }

    @Override
    public boolean isTeamWithPlayers(Integer teamId) {
        return teamDao.isTeamWithPlayers(teamId);
    }
}