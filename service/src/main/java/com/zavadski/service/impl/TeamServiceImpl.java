package com.zavadski.service.impl;

import com.zavadski.dao.TeamDao;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.springframework.stereotype.Service;

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
    public Team findTeamById(Integer teamId) {
        return this.teamDao.getTeamById(teamId);
    }

    @Override
    public Integer create(Team team) {
        return this.teamDao.create(team);
    }

    @Override
    public Integer updateTeam(Team team) {
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
    public boolean checkOnTeamWithPlayers(Integer teamId) {
        return teamDao.isTeamWithPlayers(teamId);
    }

    @Override
    public boolean checkTeamOnUnique(String teamName) {
        return teamDao.checkTeamOnUnique(teamName);
    }
}
