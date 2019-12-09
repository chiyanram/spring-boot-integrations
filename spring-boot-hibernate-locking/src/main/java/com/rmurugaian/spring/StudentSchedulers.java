package com.rmurugaian.spring;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rmurugaian 2018-07-13
 */
@Component
public class StudentSchedulers {

    private final StudentRepository studentRepository;

    public StudentSchedulers(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //@Scheduled(initialDelay = 9000, fixedDelay = 9000)
    public void runStudent() {

        final List<Student> byStatus = studentRepository.findByStatus("NEW");

        System.out.println("NO OF STUDENT " + byStatus.size());

        byStatus
                .stream()
                .peek(student -> student.setStatus("UPDATE"))
                .forEach(studentRepository::save);

        System.out.println(studentRepository.findAll());

    }
}
