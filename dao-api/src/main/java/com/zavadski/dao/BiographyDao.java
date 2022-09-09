package com.zavadski.dao;

import com.zavadski.model.Biography;

public interface BiographyDao {

    Biography getBiographyByPlayerId(Integer playerId);

}
