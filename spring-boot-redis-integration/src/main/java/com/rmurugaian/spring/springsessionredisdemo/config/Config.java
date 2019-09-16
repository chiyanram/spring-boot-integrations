package com.rmurugaian.spring.springsessionredisdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class Config {

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        final LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.setHostName(hostName);
        lettuceConnectionFactory.setPassword(password);
        lettuceConnectionFactory.setPort(port);

        return lettuceConnectionFactory;
    }

}
