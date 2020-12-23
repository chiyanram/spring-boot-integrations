package com.rmurugaian.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rmurugaian 2019-11-26
 */
@RestController
public class SwaggerController {

    @GetMapping("/cancellations")
    public String partialCancelRedemptionRequest() {
        return "DONE";
    }
}
