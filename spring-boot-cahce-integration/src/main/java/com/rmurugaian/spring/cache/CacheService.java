package com.rmurugaian.spring.cache;

import com.google.common.collect.ImmutableSet;

/**
 * @author rmurugaian 2019-11-14
 */
public interface CacheService {

    ImmutableSet<Person> fetchAll();

    Person put(final Person person);

    Person fetch(final String name);

}
