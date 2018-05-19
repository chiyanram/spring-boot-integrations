package com.rmurugaian.spring.api;

import com.rmurugaian.spring.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author rmurugaian 2018-05-18
 */
@RestController
public class PersonApiController {

    @GetMapping("/persons")
    public List<Person> persons() {

        return Collections.emptyList();
    }

}
