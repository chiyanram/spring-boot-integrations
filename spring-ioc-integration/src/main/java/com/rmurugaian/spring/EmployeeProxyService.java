package com.rmurugaian.spring;

import org.springframework.stereotype.Service;

/**
 * @author rmurugaian 2018-05-15
 */
@Service
public class EmployeeProxyService {

    private final Employee employee;

    public EmployeeProxyService(final Employee employee) {
        this.employee = employee;
        System.out.println(this.employee.getClass());
    }

}
