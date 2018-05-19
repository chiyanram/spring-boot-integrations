package com.rmurugaian.spring.controller;

import com.rmurugaian.spring.domain.Student;
import com.rmurugaian.spring.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author rmurugaian 2018-04-06
 */
@RestController
@RequestMapping("/students")
@Slf4j
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(@RequestHeader Map<String, String> headers) {

        log.debug("HEADERS {} ", headers);
        return studentRepository.findAll();
    }
}
