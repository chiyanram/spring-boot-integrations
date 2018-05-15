package com.rmurugaian.spring;

import org.springframework.stereotype.Service;

/**
 * @author rmurugaian 2018-05-15
 */
@Service
public class EmployeService {

    private final Employee employee;

    public EmployeService(final Employee employee){
        this.employee = employee;
    }

}
