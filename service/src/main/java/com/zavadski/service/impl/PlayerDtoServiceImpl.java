package com.zavadski.service.impl;

import com.zavadski.dao.PlayerDtoDao;
import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.PlayerDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PlayerDtoServiceImpl implements PlayerDtoService {

    private final Logger logger = LogManager.getLogger(PlayerDtoServiceImpl.class);

    private final PlayerDtoDao playerDtoDao;

    public PlayerDtoServiceImpl(PlayerDtoDao playerDtoDao) {
        this.playerDtoDao = playerDtoDao;
    }

    @Override
    public List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate) {
        logger.debug("Start: filterByBirthday");
        return playerDtoDao.filterByBirthday(startDate, endDate);
    }

}
