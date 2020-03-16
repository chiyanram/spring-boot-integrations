package com.rmurugaian.spring;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rmurugaian 2019-11-26
 */
@RestController
@Api
public class SwaggerController {

    @GetMapping("/cancellations")
    public String partialCancelRedemptionRequest() {
        return "DONE";
    }
}
