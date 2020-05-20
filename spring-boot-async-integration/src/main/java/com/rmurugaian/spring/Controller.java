package com.rmurugaian.spring;

import com.rmurugaian.spring.util.SomeUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class Controller implements SomeUtility {

    private final AsyncClass asyncClass;

    public Controller(final AsyncClass asyncClass) {
        this.asyncClass = asyncClass;
    }

    @RequestMapping(value = "/slowTask", method = RequestMethod.GET)
    public ResponseEntity<?> slowTask() {
        try {
            asyncClass.asyncSlowTask();
            return new ResponseEntity<>("Accepted request to do slow task", HttpStatus.ACCEPTED);
        } catch (final RuntimeException e) {
            return new ResponseEntity<>("Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}