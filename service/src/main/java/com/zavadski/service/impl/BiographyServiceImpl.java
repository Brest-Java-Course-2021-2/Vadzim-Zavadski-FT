package com.zavadski.service.impl;

import com.zavadski.dao.BiographyDao;
import com.zavadski.model.Biography;
import com.zavadski.service.BiographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiographyServiceImpl implements BiographyService {

    private final BiographyDao biographyDao;

    @Autowired
    public BiographyServiceImpl(BiographyDao biographyDao) {
        this.biographyDao = biographyDao;
    }

    @Override
    public Biography getBiographyByPlayerId(Integer playerId) {
        return biographyDao.getBiographyByPlayerId(playerId);
    }

}
