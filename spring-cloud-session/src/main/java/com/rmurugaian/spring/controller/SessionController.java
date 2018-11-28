package com.rmurugaian.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class SessionController {

    @GetMapping("/")
    public String session(final HttpSession session) {

        String name = (String) session.getAttribute("name");
        if (name == null) {
            name = UUID.randomUUID().toString();
            session.setAttribute("name", name);
        }

        return name;
    }

}
