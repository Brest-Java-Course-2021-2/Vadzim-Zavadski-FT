package com.zavadski.rest.config;

import com.zavadski.model.Biography;
import com.google.common.cache.*;
import com.zavadski.service.BiographyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheBiographyGuavaConfig {

    public static final Logger logger = LogManager.getLogger(
            CacheBiographyGuavaConfig.class);

    private final BiographyService biographyService;

    public CacheBiographyGuavaConfig(
            final BiographyService biographyService) {
        this.biographyService = biographyService;
    }

    private LoadingCache<Integer, Biography> cache = CacheBuilder.newBuilder()
            .maximumSize(50)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<Integer, Biography>() {
                @Override
                public void onRemoval(RemovalNotification<Integer, Biography> removalNotification) {
                    logger.info("Method  onRemoval() started in class {}", getClass().getName());
                    logger.info("Removed entry: {} -> {}", removalNotification.getKey(), removalNotification.getValue());
                    logger.info("Caused: {}", removalNotification.getCause().name());
                }
            })
            .recordStats()
            .build(new CacheLoader<Integer, Biography>() {
                @Override
                public Biography load(Integer integer) throws Exception {
                    logger.info("Method  load() started in class {}", getClass().getName());
                    return biographyService.getBiographyByPlayerId(integer);
                }
            });

    public Biography cacheRun(Integer key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public CacheStats getCacheStats() {
        return cache.stats();
    }

    public void printMap() {
        logger.info("{}", cache.asMap().toString());
    }
}
