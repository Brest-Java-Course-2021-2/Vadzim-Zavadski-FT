package com.zavadski.service.impl;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamDao teamDao;

    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamDao.findAll();
    }

    @Override
    public Team getTeamById(Integer teamId) {
        return this.teamDao.getTeamById(teamId);
    }

    @Override
    public Integer create(Team team) {
        return this.teamDao.create(team);
    }

    @Override
    public Integer update(Team team) {
        return this.teamDao.update(team);
    }

    @Override
    public Integer delete(Integer teamId) {
        return this.teamDao.delete(teamId);
    }

    @Override
    public Integer count() {
        return this.teamDao.count();
    }

    @Override
    public boolean isTeamWithPlayers(Integer teamId) {
        return teamDao.isTeamWithPlayers(teamId);
    }

    @Override
    public boolean checkTeamOnUnique(String teamName) {
        return teamDao.checkTeamOnUnique(teamName);
    }
}
