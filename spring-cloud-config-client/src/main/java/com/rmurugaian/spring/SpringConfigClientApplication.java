package com.rmurugaian.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rmurugaian 2018-05-19
 */
@SpringBootApplication
public class SpringConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringConfigClientApplication.class, args);
    }
}


@RestController
@RefreshScope
class ProjectNameRestController {


    @GetMapping("/person-name")
    public String name(@Value("${configuration.name}") final String projectName) {
        return projectName;
    }

}
