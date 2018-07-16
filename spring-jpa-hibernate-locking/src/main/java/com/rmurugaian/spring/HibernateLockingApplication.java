package com.rmurugaian.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.stream.IntStream;

/**
 * @author rmurugaian 2018-07-13
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class HibernateLockingApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(final String[] args) {
        SpringApplication.run(HibernateLockingApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {

        IntStream
            .range(0, 5)
            .forEach(i -> {
                final Student student = new Student();
                student.setStatus("NEW");
                studentRepository.save(student);
            });

        System.out.println(studentRepository.findAll());
    }
}
