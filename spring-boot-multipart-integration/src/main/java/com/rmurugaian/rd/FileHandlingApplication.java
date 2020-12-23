package com.rmurugaian.rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FileHandlingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FileHandlingApplication.class, args);
    }

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }
}
