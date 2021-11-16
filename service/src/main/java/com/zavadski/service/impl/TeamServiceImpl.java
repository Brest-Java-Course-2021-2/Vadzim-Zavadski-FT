package com.zavadski.service.impl;

import com.zavadski.dao.TeamDao;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

    private final Logger logger = LogManager.getLogger(TeamServiceImpl.class);

    private final TeamDao teamDao;

    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    @Transactional
    public Integer create(Team team) {
        logger.debug("create({})", team);
        return this.teamDao.create(team);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        logger.debug("count()");
        return this.teamDao.count();
    }
}