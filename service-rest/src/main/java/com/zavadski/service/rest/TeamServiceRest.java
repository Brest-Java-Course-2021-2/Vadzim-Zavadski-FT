package com.zavadski.service.rest;

import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TeamServiceRest implements TeamService {

    private String url;

    private RestTemplate restTemplate;

    public TeamServiceRest() {
    }

    public TeamServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Team> getAllTeams() {

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Team>) responseEntity.getBody();
    }

    @Override
    public Team findTeamById(Integer teamId) {

        ResponseEntity<Team> responseEntity =
                restTemplate.getForEntity(url + "/" + teamId, Team.class);
        return responseEntity.getBody();
    }

    @Override
    public Integer create(Team team) {

        ResponseEntity responseEntity = restTemplate.postForEntity(url, team, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer updateTeam(Team team) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Team> entity = new HttpEntity<>(team, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(
                url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer delete(Integer teamId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Team> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + teamId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer count() {

        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(url + "/count", Integer.class);
        return responseEntity.getBody();
    }

    @Override
    public boolean checkOnTeamWithPlayers(Integer teamId) {
        Boolean result = restTemplate.getForObject(url + "/check/" + teamId, Boolean.class);
        return result;
    }

    @Override
    public boolean checkTeamOnUnique(String teamName) {
        return restTemplate.getForObject(url + "/unique/" + teamName, Boolean.class);
    }

}
