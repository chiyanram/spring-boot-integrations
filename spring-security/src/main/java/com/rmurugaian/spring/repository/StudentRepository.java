package com.rmurugaian.spring.repository;

import com.rmurugaian.spring.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rmurugaian 2018-04-06
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
