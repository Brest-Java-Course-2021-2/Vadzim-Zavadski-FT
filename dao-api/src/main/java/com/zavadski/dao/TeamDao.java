package com.zavadski.dao;

import com.zavadski.model.Team;

import java.util.List;

public interface TeamDao {

    List<Team> findAll();

    Integer create(Team team);

    Integer update(Team team);

    Integer delete(Team team);

}
