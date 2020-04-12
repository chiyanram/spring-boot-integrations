package com.rmurugaian.spring;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author rmurugaian 2018-05-22
 */
@RestController
public class EmployeeRestController {

    private final HostToVarConfiguration hostToVarConfiguration;

    public EmployeeRestController(final HostToVarConfiguration hostToVarConfiguration) {
        this.hostToVarConfiguration = hostToVarConfiguration;
    }

    @PostMapping("/sets")
    public Set<String> sets(@RequestParam final Set<String> names) {
        return names;
    }
}
