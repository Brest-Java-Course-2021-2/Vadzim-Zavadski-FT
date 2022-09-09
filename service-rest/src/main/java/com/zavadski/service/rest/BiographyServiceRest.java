package com.zavadski.service.rest;

import com.zavadski.model.Biography;
import com.zavadski.service.BiographyService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BiographyServiceRest implements BiographyService {

    private String url;

    private RestTemplate restTemplate;

    public BiographyServiceRest() {
    }

    public BiographyServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Biography getBiographyByPlayerId(Integer playerId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Integer> entity = new HttpEntity<>(playerId, headers);
        ResponseEntity<Biography> responseEntity = restTemplate.exchange(
                url + "/" + playerId, HttpMethod.GET, entity, Biography.class);
        return responseEntity.getBody();
    }

}
