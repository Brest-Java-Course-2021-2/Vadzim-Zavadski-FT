package com.zavadski.service.impl;

import com.zavadski.dao.api.TeamDtoDao;
import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.TeamDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamDtoServiceImpl implements TeamDtoService {

    private final Logger logger = LogManager.getLogger(TeamDtoServiceImpl.class);

    private final TeamDtoDao teamDtoDao;

    public TeamDtoServiceImpl(TeamDtoDao teamDtoDao) {
        this.teamDtoDao = teamDtoDao;
    }

    @Override
    public List<TeamDto> findAllWithNumberOfPlayers() {
        logger.debug("Start: findAllWithNumberOfPlayers");
        return teamDtoDao.findAllWithNumberOfPlayers();
    }
}
