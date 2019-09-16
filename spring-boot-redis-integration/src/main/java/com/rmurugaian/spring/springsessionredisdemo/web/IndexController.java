package com.rmurugaian.spring.springsessionredisdemo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class IndexController {

    @GetMapping("/get/{name}")
    public String get(@PathVariable String name, HttpSession session) {
        return (String) session.getAttribute(name);
    }

    @GetMapping("/put/{name}/{value}")
    public String put(@PathVariable String name, @PathVariable String value, HttpSession session) {
        session.setAttribute(name, value);
        return (String) session.getAttribute(name);
    }

}
