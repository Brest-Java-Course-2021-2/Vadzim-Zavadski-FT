package com.zavadski.service.impl;

import com.zavadski.dao.PlayerDtoDao;
import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.PlayerDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PlayerDtoServiceImpl implements PlayerDtoService {

    private final PlayerDtoDao playerDtoDao;

    public PlayerDtoServiceImpl(PlayerDtoDao playerDtoDao) {
        this.playerDtoDao = playerDtoDao;
    }

    @Override
    public List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate) {
        return playerDtoDao.filterByBirthday(startDate, endDate);
    }
}
