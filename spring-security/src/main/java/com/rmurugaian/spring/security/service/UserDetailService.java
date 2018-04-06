package com.rmurugaian.spring.security.service;

import com.rmurugaian.spring.domain.User;
import com.rmurugaian.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author rmurugaian 2018-04-06
 */
@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User byUserName = userRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(
            byUserName.getUserName(),
            byUserName.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(byUserName.getRole())));
    }
}
