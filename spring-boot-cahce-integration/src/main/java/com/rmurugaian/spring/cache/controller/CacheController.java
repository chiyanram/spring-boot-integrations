package com.rmurugaian.spring.cache.controller;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.spring.cache.domain.Person;
import com.rmurugaian.spring.cache.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rmurugaian 2019-11-14
 */
@RestController
public class CacheController {

    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    private final CacheService cacheService;

    public CacheController(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/all")
    public ImmutableSet<Person> all() {
        logger.debug("Invoke All persons controller");
        return cacheService.fetchAll();
    }

}
