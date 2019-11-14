package com.rmurugaian.spring.cache.service;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.spring.cache.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author rmurugaian 2019-11-14
 */
@Service
@CacheConfig(cacheNames = "instruments")
public class DefaultCacheService implements CacheService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCacheService.class);

    @Override
    @Cacheable(key = "'PERSONS'")
    public ImmutableSet<Person> fetchAll() {
        logger.info("Invoking person caches");

        final Person element = new Person();
        element.setName("Ram");

        return ImmutableSet.<Person>builder()
            .add(element)
            .build();
    }

    @Override
    public Person put(final Person person) {
        throw new UnsupportedOperationException("Not Implemented.");
    }

    @Override
    public Person fetch(final String name) {
        throw new UnsupportedOperationException("Not Implemented.");
    }
}
