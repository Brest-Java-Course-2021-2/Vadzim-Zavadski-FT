package com.zavadski.service.rest;

import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.PlayerDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerDtoServiceRest implements PlayerDtoService {

    private final Logger logger = LogManager.getLogger(PlayerDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public PlayerDtoServiceRest() {

    }

    public PlayerDtoServiceRest(String url, RestTemplate restTemplate) {
        this();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate) {
        logger.debug("findAllWithNumberOfPlayers()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<PlayerDto>) responseEntity.getBody();
    }
}
