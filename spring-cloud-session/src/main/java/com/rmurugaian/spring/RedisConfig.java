package com.rmurugaian.spring;

import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class RedisConfig {

    private RedisServer redisServer;


    @PostConstruct
    public void init() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }


    @PreDestroy
    public void close() {
        redisServer.stop();
    }

}

