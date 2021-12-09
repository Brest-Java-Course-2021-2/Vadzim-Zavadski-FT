package com.zavadski.service.rest;

import com.zavadski.model.dto.TeamDto;
import com.zavadski.service.TeamDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TeamDtoServiceRest implements TeamDtoService {

    private final Logger logger = LogManager.getLogger(TeamDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public TeamDtoServiceRest() {

    }

    public TeamDtoServiceRest(String url, RestTemplate restTemplate) {
        this();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TeamDto> findAllWithNumberOfPlayers() {
        logger.debug("findAllWithNumberOfPlayers()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<TeamDto>) responseEntity.getBody();
    }
}