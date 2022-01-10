package com.zavadski.web_app.config;

import com.zavadski.service.PlayerDtoService;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamDtoService;
import com.zavadski.service.TeamService;
import com.zavadski.service.rest.PlayerDtoServiceRest;
import com.zavadski.service.rest.PlayerServiceRest;
import com.zavadski.service.rest.TeamDtoServiceRest;
import com.zavadski.service.rest.TeamServiceRest;
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
}
