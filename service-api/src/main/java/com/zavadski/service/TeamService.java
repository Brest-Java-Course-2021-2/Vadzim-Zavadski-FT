package com.zavadski.service;

import com.zavadski.model.Team;

public interface TeamService {

    Integer create(Team team);

    Integer count();
}