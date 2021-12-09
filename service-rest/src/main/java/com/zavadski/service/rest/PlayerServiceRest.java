package com.zavadski.service.rest;

import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceRest implements PlayerService {

    private final Logger logger = LogManager.getLogger(PlayerDtoServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public PlayerServiceRest() {
    }

    public PlayerServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Player> findAllPlayers() {
        logger.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Player>) responseEntity.getBody();
    }

    @Override
    public Optional<Player> getPlayerById(Integer playerId) {
        logger.debug("findById({})", playerId);
        ResponseEntity<Player> responseEntity =
                restTemplate.getForEntity(url + "/" + playerId, Player.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Integer create(Player player) {
        logger.debug("create({})", player);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, player, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer update(Player player) {

        logger.debug("update({})", player);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Player> entity = new HttpEntity<>(player, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer delete(Integer playerId) {
        logger.debug("delete({})", playerId);
        //restTemplate.delete(url + "/" + playerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Player> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + playerId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }
}