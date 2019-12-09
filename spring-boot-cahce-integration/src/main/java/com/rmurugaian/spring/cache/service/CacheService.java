package com.rmurugaian.spring.cache.service;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.spring.cache.domain.Person;

/**
 * @author rmurugaian 2019-11-14
 */
public interface CacheService {

    ImmutableSet<Person> fetchAll();

    Person save(final Person person);

    Person put(final Person person);

    Person fetch(final String name);

}