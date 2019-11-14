package com.rmurugaian.spring.cache;

import com.google.common.collect.ImmutableSet;
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
        logger.debug("Invoke All persons");
        return cacheService.fetchAll();
    }
}
