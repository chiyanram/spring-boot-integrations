package com.rmurugaian.spring.cache;

import com.rmurugaian.spring.cache.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rmurugaian 2019-12-09
 */
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByName(String name);

}
