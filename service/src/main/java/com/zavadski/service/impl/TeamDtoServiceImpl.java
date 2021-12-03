package com.zavadski.service.impl;

import com.zavadski.dao.TeamDtoDao;
import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.TeamDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamDtoServiceImpl implements TeamDtoService {

    private final TeamDtoDao teamDtoDao;

    public TeamDtoServiceImpl(TeamDtoDao teamDtoDao) {
        this.teamDtoDao = teamDtoDao;
    }

    @Override
    public List<TeamDto> findAllWithNumberOfPlayers() {
        return teamDtoDao.findAllWithNumberOfPlayers();
    }

}