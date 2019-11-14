package com.rmurugaian.spring.cache.service;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.spring.cache.domain.Person;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author rmurugaian 2019-11-14
 */
@Service
@CacheConfig(cacheNames = "instruments")
public class DefaultCacheService implements CacheService {

    @Override
    @Cacheable(key = "'PERSONS'")
    public ImmutableSet<Person> fetchAll() {
        System.out.println("LOAD FILES");
        final Person element = new Person();
        element.setName("Ram");
        return ImmutableSet.<Person>builder()
            .add(element)
            .build();
    }

    @Override
    public Person put(final Person person) {
        return null;
    }

    @Override
    public Person fetch(final String name) {
        return null;
    }
}
