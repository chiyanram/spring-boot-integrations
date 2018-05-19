package com.rmurugaian.spring;

import com.rmurugaian.spring.domain.User;
import com.rmurugaian.spring.filter.LoggingFilter;
import com.rmurugaian.spring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.nio.file.Files;
import java.util.Collections;

/**
 * @author rmurugaian 2018-04-06
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories
@Slf4j
public class SpringSecurityApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    public static void main(final String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        Files.lines(new File("D:\\projects\\Spring\\spring-security\\src\\main\\resources\\users.txt").toPath())
            .map(line -> line.split(","))
            .map(array -> new User(array[0], passwordEncoder().encode(array[1]), array[2]))
            .forEach(userRepository::save);

        userRepository.findAll().forEach(user -> log.error("USer : {}", user));
    }

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilterFilterRegistrationBean() {

        final FilterRegistrationBean<LoggingFilter> loggingFilterFilterRegistrationBean =
            new FilterRegistrationBean<>();
        loggingFilterFilterRegistrationBean.setFilter(new LoggingFilter());
        loggingFilterFilterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));

        return loggingFilterFilterRegistrationBean;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
