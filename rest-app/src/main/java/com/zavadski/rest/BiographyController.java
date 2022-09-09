package com.zavadski.rest;

import com.zavadski.model.Biography;
import com.zavadski.rest.config.CacheBiographyGuavaConfig;
import com.zavadski.service.BiographyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BiographyController {

    public static final Logger logger = LogManager.getLogger(BiographyController.class);

    private final BiographyService biographyService;

    private final CacheBiographyGuavaConfig cacheBiographyGuavaConfig;

    public BiographyController(
            final BiographyService biographyService,
            final CacheBiographyGuavaConfig biographyWithGuavaConfig) {
        this.biographyService = biographyService;
        this.cacheBiographyGuavaConfig = biographyWithGuavaConfig;
    }

    @GetMapping(value = "/biography/{playerId}")
    public ResponseEntity<Biography> getBiographyByPlayerId(
            @PathVariable("playerId") final Integer playerId) {

        logger.info("Method getBiographyByPlayerId() {} started of class {} started",
                playerId, getClass().getName());

        if (cacheBiographyGuavaConfig.cacheRun(playerId) != null) {
            cacheBiographyGuavaConfig.printMap();
            logger.info("{}", cacheBiographyGuavaConfig.getCacheStats().toString());
            logger.info("-------------------------------------------------------------"
                    + "------------------------------------------------------------");
            return new ResponseEntity<>(cacheBiographyGuavaConfig.cacheRun(playerId), HttpStatus.OK);
        } else {

            Biography biography =
                    biographyService.getBiographyByPlayerId(playerId);

            cacheBiographyGuavaConfig.printMap();
            logger.info("{}", cacheBiographyGuavaConfig.getCacheStats().toString());
            logger.info("-------------------------------------------------------------"
                    + "------------------------------------------------------------");

            return new ResponseEntity<>(biography, HttpStatus.OK);
        }
    }
}
