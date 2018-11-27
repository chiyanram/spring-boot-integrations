package com.rmurugaian.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties
public class ConfigurationBasicApplication {

    @Value("${configuration.name}")
    public void setProjectName(final String projectName) {
        System.out.println("setting project name " + projectName);
    }

    @Autowired
    public void setEnvironment(final Environment environment) {
        System.out.println("Project name from environemnt " + environment.getProperty("configuration.name"));
    }

    @Autowired
    public void setConfig(final Config config) {

        System.out.println("Project Name from object " + config.getName());
    }


    public static void main(String[] args) {
        SpringApplication.run(ConfigurationBasicApplication.class, args);
    }

}


@Configuration
@ConfigurationProperties(prefix = "configuration")
class Config {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}