package com.zavadski.service;

import com.zavadski.model.dto.PlayerDto;

import java.time.LocalDate;
import java.util.List;

public interface PlayerDtoService {

    List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate);

}
