package com.rmurugaian.spring.validation.controller;

import com.rmurugaian.spring.validation.domain.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {

    @PostMapping("/employees")
    public Employee save(@RequestBody final Employee employee) {
        return employee;
    }
}
