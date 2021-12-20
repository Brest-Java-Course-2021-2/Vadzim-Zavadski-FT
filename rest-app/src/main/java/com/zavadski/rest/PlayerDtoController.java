package com.zavadski.rest;

import com.zavadski.dao.PlayerDaoJDBCImpl;
import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.PlayerDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
public class PlayerDtoController {

    private static final Logger logger = LogManager.getLogger(PlayerDtoController.class);

    private final PlayerDtoService playerDtoService;

    public PlayerDtoController(PlayerDtoService playerDtoService) {
        this.playerDtoService = playerDtoService;
    }

    @GetMapping(value = "player_dtos")
    public final Collection<PlayerDto> playerDtos(@RequestParam(name = "startDate", required = false)
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @RequestParam(name = "endDate", required = false)
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        logger.debug("rest-app: playerDtos()");
        return playerDtoService.filterByBirthday(startDate, endDate);
    }
}
