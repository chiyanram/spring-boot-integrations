package com.rmurugaian.rd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author rmurugaian 2018-12-27
 */
@SpringBootApplication
public class JavaMssqlApplication {

    public static void main(final String[] args) {
        SpringApplication.run(JavaMssqlApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {

        return jackson2ObjectMapperBuilder.modulesToInstall(new JodaModule(), new GuavaModule()).build();
    }
}
