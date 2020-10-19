package com.rmurugaian.rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
