package com.zavadski.service.rest.mongo;

import com.zavadski.mongo.model.document.PlayersDocument;
import com.zavadski.mongo.service.WriteToMongoService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WriteToMongoServiceRest implements WriteToMongoService {

    private String url;

    private RestTemplate restTemplate;

    public WriteToMongoServiceRest() {
    }

    public WriteToMongoServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public PlayersDocument createCollection() {

        ResponseEntity<PlayersDocument> responseEntity = restTemplate.getForEntity(url, PlayersDocument.class);
        return responseEntity.getBody();
    }
}
