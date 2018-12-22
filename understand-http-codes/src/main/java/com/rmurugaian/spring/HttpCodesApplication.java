package com.rmurugaian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/http")
class HttpCodesRestController {

    private static final Map<Integer, HttpStatus> HTTP_STATUS_MAP =
            Arrays.stream(HttpStatus.values())
                    .collect(HashMap::new, (map, status) -> map.put(status.value(), status), HashMap::putAll);

    @GetMapping("/{code}")
    public ResponseEntity<String> httpCode(@PathVariable final Integer code) {

        final HttpStatus httpStatus = HTTP_STATUS_MAP.get(code);

        return ResponseEntity.status(httpStatus).body(httpStatus.getReasonPhrase());
    }
}

@SpringBootApplication
public class HttpCodesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpCodesApplication.class, args);
    }

}
