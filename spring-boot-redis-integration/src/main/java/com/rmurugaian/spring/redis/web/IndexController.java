package com.rmurugaian.spring.redis.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class IndexController {

    @GetMapping("/get/{name}")
    public String get(@PathVariable final String name, final HttpSession session) {
        return (String) session.getAttribute(name);
    }

    @GetMapping("/put/{name}/{value}")
    public String put(@PathVariable final String name, @PathVariable final String value, final HttpSession session) {
        session.setAttribute(name, value);
        return (String) session.getAttribute(name);
    }

}
