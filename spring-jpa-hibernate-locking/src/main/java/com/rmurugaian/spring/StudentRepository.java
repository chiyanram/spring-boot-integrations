package com.rmurugaian.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author rmurugaian 2018-07-13
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStatus(final String status);
}
