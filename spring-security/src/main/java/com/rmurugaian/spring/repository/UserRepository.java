package com.rmurugaian.spring.repository;

import com.rmurugaian.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rmurugaian 2018-04-06
 */
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserName(final String userName);

}
