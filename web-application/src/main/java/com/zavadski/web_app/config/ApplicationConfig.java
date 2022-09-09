package com.zavadski.web_app.config;

import com.zavadski.model.Biography;
import com.zavadski.mongo.service.WriteToMongoService;
import com.zavadski.service.*;
import com.zavadski.service.rest.*;
import com.zavadski.service.rest.mongo.WriteToMongoServiceRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class ApplicationConfig {

    @Value("${rest.server.protocol}")
    private String protocol;
    @Value("${rest.server.host}")
    private String host;
    @Value("${rest.server.port}")
    private Integer port;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    @Bean
    TeamDtoService teamDtoService() {
        String url = String.format("%s://%s:%d/team_dtos", protocol, host, port);
        return new TeamDtoServiceRest(url, restTemplate());
    }

    @Bean
    TeamService teamService() {
        String url = String.format("%s://%s:%d/teams", protocol, host, port);
        return new TeamServiceRest(url, restTemplate());
    }

    @Bean
    PlayerDtoService playerDtoService() {
        String url = String.format("%s://%s:%d/player_dtos", protocol, host, port);
        return new PlayerDtoServiceRest(url, restTemplate());
    }

    @Bean
    PlayerService playerService() {
        String url = String.format("%s://%s:%d/players", protocol, host, port);
        return new PlayerServiceRest(url, restTemplate());
    }

    @Bean
    BiographyService biographyService() {
        String url = String.format("%s://%s:%d/biography", protocol, host, port);
        return new BiographyServiceRest(url, restTemplate());
    }

    @Bean
    WriteToMongoService writeToMongoService() {
        String url = String.format("%s://%s:%d/write_to_mongo", protocol, host, port);
        return new WriteToMongoServiceRest(url, restTemplate());
    }
}
