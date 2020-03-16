package com.rmurugaian.spring.validation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    @PostMapping("/employees")
    public Employee save(@Valid @RequestBody final Employee employee) {
        return employee;
    }
}
