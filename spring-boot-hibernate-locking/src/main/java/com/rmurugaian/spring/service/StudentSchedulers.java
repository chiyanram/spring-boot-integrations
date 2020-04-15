package com.rmurugaian.spring.service;

import com.rmurugaian.spring.repository.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author rmurugaian 2018-07-13
 */
@Component class StudentSchedulers {

    private final StudentRepository studentRepository;

     StudentSchedulers(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Scheduled(initialDelay = 9000, fixedDelay = 9000)
    public void runStudent() {

        studentRepository.findByStatus("NEW")
            .stream()
            .peek(student -> student.setStatus("UPDATE"))
            .forEach(studentRepository::save);
    }
}
