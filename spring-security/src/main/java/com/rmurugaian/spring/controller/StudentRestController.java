package com.rmurugaian.spring.controller;

import com.rmurugaian.spring.domain.Student;
import com.rmurugaian.spring.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rmurugaian 2018-04-06
 */
@RestController
@RequestMapping("/students")
public class StudentRestController {


    private final StudentRepository studentRepository;

    public StudentRestController(final StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }


    @GetMapping
    public List<Student> getStudents() {

        return studentRepository.findAll();
    }
}
